import struct, sys

def uleb(d, o):
    r = 0; s = 0
    while True:
        b = d[o]; o += 1
        r |= (b & 0x7f) << s; s += 7
        if b < 0x80: return r, o

def sleb(d, o):
    r = 0; s = 0
    while True:
        b = d[o]; o += 1
        r |= (b & 0x7f) << s; s += 7
        if b < 0x80:
            if b & 0x40: r -= 1 << s
            return r, o

OPS = {}
def op(c, n, f): OPS[c] = (n, f)
for c, n, f in [
 (0,'nop','10x'),(1,'move','12x'),(2,'move/from16','22x'),(3,'move/16','32x'),
 (4,'move-wide','12x'),(5,'move-wide/from16','22x'),(6,'move-wide/16','32x'),
 (7,'move-object','12x'),(8,'move-object/from16','22x'),(9,'move-object/16','32x'),
 (0xa,'move-result','11x'),(0xb,'move-result-wide','11x'),(0xc,'move-result-object','11x'),(0xd,'move-exception','11x'),
 (0xe,'return-void','10x'),(0xf,'return','11x'),(0x10,'return-wide','11x'),(0x11,'return-object','11x'),
 (0x12,'const/4','11n'),(0x13,'const/16','21s'),(0x14,'const','31i'),(0x15,'const/high16','21h'),
 (0x16,'const-wide/16','21s'),(0x17,'const-wide/32','31i'),(0x18,'const-wide','51l'),(0x19,'const-wide/high16','21h'),
 (0x1a,'const-string','21c'),(0x1b,'const-string/jumbo','31c'),(0x1c,'const-class','21c'),
 (0x1d,'monitor-enter','11x'),(0x1e,'monitor-exit','11x'),(0x1f,'check-cast','21c'),(0x20,'instance-of','22c'),
 (0x21,'array-length','12x'),(0x22,'new-instance','21c'),(0x23,'new-array','22c'),(0x24,'filled-new-array','35c'),
 (0x25,'filled-new-array/range','3rc'),(0x26,'fill-array-data','31t'),(0x27,'throw','11x'),(0x28,'goto','10t'),
 (0x29,'goto/16','20t'),(0x2a,'goto/32','30t'),(0x2b,'packed-switch','31t'),(0x2c,'sparse-switch','31t')]:
    op(c, n, f)
for i, n in enumerate(['cmpl-float','cmpg-float','cmpl-double','cmpg-double','cmp-long']): op(0x2d+i, n, '23x')
for i, n in enumerate(['eq','ne','lt','ge','gt','le']):
    op(0x32+i, 'if-'+n, '22t'); op(0x38+i, 'if-'+n+'z', '21t')
for c in list(range(0x3e,0x44))+[0x73,0x79,0x7a]+list(range(0xe3,0xfa)): op(c,'unused','10x')
sfx = ['','-wide','-object','-boolean','-byte','-char','-short']
for i, s in enumerate(sfx):
    op(0x44+i,'aget'+s,'23x'); op(0x4b+i,'aput'+s,'23x')
    op(0x52+i,'iget'+s,'22c'); op(0x59+i,'iput'+s,'22c')
    op(0x60+i,'sget'+s,'21c'); op(0x67+i,'sput'+s,'21c')
for i, n in enumerate(['virtual','super','direct','static','interface']):
    op(0x6e+i,'invoke-'+n,'35c'); op(0x74+i,'invoke-'+n+'/range','3rc')
un = ['neg-int','not-int','neg-long','not-long','neg-float','neg-double','int-to-long','int-to-float','int-to-double','long-to-int','long-to-float','long-to-double','float-to-int','float-to-long','float-to-double','double-to-int','double-to-long','double-to-float','int-to-byte','int-to-char','int-to-short']
for i, n in enumerate(un): op(0x7b+i, n, '12x')
bn = []
for t in ['int','long']:
    bn += [x+'-'+t for x in ['add','sub','mul','div','rem','and','or','xor','shl','shr','ushr']]
for t in ['float','double']:
    bn += [x+'-'+t for x in ['add','sub','mul','div','rem']]
for i, n in enumerate(bn): op(0x90+i, n, '23x'); op(0xb0+i, n+'/2addr', '12x')
for i, n in enumerate(['add','rsub','mul','div','rem','and','or','xor']): op(0xd0+i, n+'-int/lit16', '22s')
for i, n in enumerate(['add','rsub','mul','div','rem','and','or','xor','shl','shr','ushr']): op(0xd8+i, n+'-int/lit8', '22b')
op(0xfa,'invoke-polymorphic','45cc'); op(0xfb,'invoke-polymorphic/range','4rcc')
op(0xfc,'invoke-custom','35c'); op(0xfd,'invoke-custom/range','3rc')
op(0xfe,'const-method-handle','21c'); op(0xff,'const-method-type','21c')
SIZE = {'10x':1,'12x':1,'11n':1,'11x':1,'10t':1,'20t':2,'22x':2,'21t':2,'21s':2,'21h':2,'21c':2,'23x':2,'22b':2,'22t':2,'22s':2,'22c':2,
        '32x':3,'30t':3,'31t':3,'31i':3,'31c':3,'35c':3,'3rc':3,'45cc':4,'4rcc':4,'51l':5}

class Dex:
    def __init__(self, path):
        self.path = path
        d = self.d = bytearray(open(path,'rb').read())
        h = struct.unpack_from('<8sI20sIIIIIIIIIIIIIIIIIIII', d, 0)
        (_, _, _, self.file_size, _, _, _, _, _, self.str_n, self.str_off, self.type_n, self.type_off,
         self.proto_n, self.proto_off, self.field_n, self.field_off, self.meth_n, self.meth_off,
         self.cls_n, self.cls_off, _, _) = h
        self._s = {}
    def string(self, i):
        if i in self._s: return self._s[i]
        o = struct.unpack_from('<I', self.d, self.str_off+4*i)[0]
        n, o = uleb(self.d, o)
        e = self.d.index(0, o)
        try: s = bytes(self.d[o:e]).decode('utf-8')
        except: s = bytes(self.d[o:e]).decode('latin1')
        self._s[i] = s; return s
    def type(self, i):
        return self.string(struct.unpack_from('<I', self.d, self.type_off+4*i)[0])
    def proto(self, i):
        sh, rt, po = struct.unpack_from('<III', self.d, self.proto_off+12*i)
        ps = []
        if po:
            n = struct.unpack_from('<I', self.d, po)[0]
            ps = [self.type(struct.unpack_from('<H', self.d, po+4+2*k)[0]) for k in range(n)]
        return '(' + ''.join(ps) + ')' + self.type(rt)
    def field(self, i):
        c, t, n = struct.unpack_from('<HHI', self.d, self.field_off+8*i)
        return '%s->%s:%s' % (self.type(c), self.string(n), self.type(t))
    def method(self, i):
        c, p, n = struct.unpack_from('<HHI', self.d, self.meth_off+8*i)
        return '%s->%s%s' % (self.type(c), self.string(n), self.proto(p))
    def classes(self):
        for i in range(self.cls_n):
            o = self.cls_off + 32*i
            ci, af, sup, itf, src, ann, cd, sv = struct.unpack_from('<IIIIIIII', self.d, o)
            yield self.type(ci), cd
    def methods_of(self, cd):
        if not cd: return []
        d = self.d; o = cd
        sf, o = uleb(d, o); inf, o = uleb(d, o); dm, o = uleb(d, o); vm, o = uleb(d, o)
        for _ in range(sf+inf): _, o = uleb(d, o); _, o = uleb(d, o)
        out = []
        for cnt in (dm, vm):
            idx = 0
            for _ in range(cnt):
                di, o = uleb(d, o); idx += di
                af, o = uleb(d, o); co, o = uleb(d, o)
                out.append((idx, af, co))
        return out
    def code(self, co):
        regs, ins, outs, tries, dbg, n = struct.unpack_from('<HHHHII', self.d, co)
        return regs, ins, co+16, n
    def ref(self, f, idx, name):
        if name.startswith('const-string'): return repr(self.string(idx))
        if name in ('const-class','check-cast','instance-of','new-instance','new-array') or name.startswith('filled-new-array'): return self.type(idx)
        if name[0] in 'is' and ('get' in name or 'put' in name): return self.field(idx)
        if name.startswith('invoke') and 'custom' not in name: return self.method(idx)
        return '#%d' % idx
    def disasm(self, co):
        regs, ins, start, n = self.code(co)
        d = self.d; pc = 0; lines = []
        def u16(k): return struct.unpack_from('<H', d, start+2*k)[0]
        def s16(k): return struct.unpack_from('<h', d, start+2*k)[0]
        while pc < n:
            w = u16(pc); opc = w & 0xff; hi = w >> 8
            if opc == 0 and hi in (1,2,3):
                if hi == 1: sz = 4 + u16(pc+1)*2
                elif hi == 2: sz = 2 + u16(pc+1)*4
                else:
                    ew = u16(pc+1); cnt = u16(pc+2) | (u16(pc+3) << 16); sz = 4 + (ew*cnt+1)//2
                lines.append((pc, 'payload', [hi], start+2*pc)); pc += sz; continue
            name, f = OPS[opc]; sz = SIZE[f]; a = []
            A4 = hi & 0xf; B4 = hi >> 4
            if f == '12x': a = ['v%d'%A4, 'v%d'%B4]
            elif f == '11n': a = ['v%d'%A4, (B4 ^ 8) - 8]
            elif f == '11x': a = ['v%d'%hi]
            elif f == '10t': a = [pc + ((hi ^ 0x80) - 0x80)]
            elif f == '20t': a = [pc + s16(pc+1)]
            elif f == '22x': a = ['v%d'%hi, 'v%d'%u16(pc+1)]
            elif f == '21t': a = ['v%d'%hi, pc + s16(pc+1)]
            elif f == '21s': a = ['v%d'%hi, s16(pc+1)]
            elif f == '21h': a = ['v%d'%hi, s16(pc+1)]
            elif f == '21c': a = ['v%d'%hi, self.ref(f, u16(pc+1), name)]
            elif f == '23x': b = u16(pc+1); a = ['v%d'%hi, 'v%d'%(b&0xff), 'v%d'%(b>>8)]
            elif f == '22b': b = u16(pc+1); a = ['v%d'%hi, 'v%d'%(b&0xff), ((b>>8)^0x80)-0x80]
            elif f == '22t': a = ['v%d'%A4, 'v%d'%B4, pc + s16(pc+1)]
            elif f == '22s': a = ['v%d'%A4, 'v%d'%B4, s16(pc+1)]
            elif f == '22c': a = ['v%d'%A4, 'v%d'%B4, self.ref(f, u16(pc+1), name)]
            elif f == '32x': a = ['v%d'%u16(pc+1), 'v%d'%u16(pc+2)]
            elif f in ('30t',): a = [pc + struct.unpack_from('<i', d, start+2*pc+2)[0]]
            elif f == '31t': a = ['v%d'%hi, pc + struct.unpack_from('<i', d, start+2*pc+2)[0]]
            elif f == '31i': a = ['v%d'%hi, struct.unpack_from('<i', d, start+2*pc+2)[0]]
            elif f == '31c': a = ['v%d'%hi, self.ref(f, struct.unpack_from('<I', d, start+2*pc+2)[0], name)]
            elif f == '51l': a = ['v%d'%hi, struct.unpack_from('<q', d, start+2*pc+2)[0]]
            elif f in ('35c','45cc'):
                cnt = B4; g = A4; idx = u16(pc+1); r = u16(pc+2)
                rr = [r & 0xf, (r>>4)&0xf, (r>>8)&0xf, (r>>12)&0xf, g][:cnt]
                a = ['{' + ','.join('v%d'%x for x in rr) + '}', self.ref(f, idx, name)]
            elif f in ('3rc','4rcc'):
                idx = u16(pc+1); c0 = u16(pc+2)
                a = ['{v%d..v%d}' % (c0, c0+hi-1), self.ref(f, idx, name)]
            lines.append((pc, name, a, start+2*pc)); pc += sz
        return lines

def load_all(paths):
    return [Dex(p) for p in paths]
