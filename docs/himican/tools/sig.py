import sys, os, re, json, hashlib
sys.path.insert(0,'/home/claude/tools')
from dex import Dex
def load(dexdir):
    out={}
    files=sorted([f for f in os.listdir(dexdir) if f.endswith('.dex')], key=lambda f:(len(f),f))
    for f in files:
        d=Dex(os.path.join(dexdir,f))
        for c,cd in d.classes():
            if not c.startswith('Lcom/example/wayfindr/') or c in out: continue
            ms={}
            for mi,af,co in d.methods_of(cd):
                m=d.method(mi).split('->',1)[1]
                if co:
                    try: L=d.disasm(co)
                    except Exception as e: ms[m]='ERR'; continue
                    txt='\n'.join(n+' '+' '.join(str(x) for x in a if not isinstance(x,int) or n.startswith('const')) for _,n,a,_ in L if n!='nop')
                else: txt=''
                ms[m]=hashlib.md5(txt.encode()).hexdigest()
            out[c]=ms
    return out
a=load(sys.argv[1]); b=load(sys.argv[2])
json.dump({'a':a,'b':b},open(sys.argv[3],'w'))
onlyb=sorted(set(b)-set(a)); onlya=sorted(set(a)-set(b))
chg={}
for c in set(a)&set(b):
    ma,mb=a[c],b[c]
    diff=[m for m in set(ma)|set(mb) if ma.get(m)!=mb.get(m)]
    if diff: chg[c]=diff
print('upstream classes',len(a),'apk classes',len(b),'same-name changed',len(chg),'new',len(onlyb),'removed',len(onlya))
tot=sum(len(v) for v in a.values()); print('upstream methods',tot)
