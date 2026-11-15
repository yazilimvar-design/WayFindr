import sys, os
sys.path.insert(0, '/home/claude/tools')
from dex import Dex
out = sys.argv[1]
for p in sys.argv[2:]:
    dx = Dex(p); tag = os.path.basename(p)
    for cname, cd in dx.classes():
        if not cname.startswith('Lcom/example/wayfindr/'): continue
        fn = os.path.join(out, cname[1:-1].replace('/', '.').replace('$','__') + '.txt')
        with open(fn, 'w') as f:
            f.write('# %s %s\n' % (cname, tag))
            for mi, af, co in dx.methods_of(cd):
                f.write('\n.method %s  [code@%d]\n' % (dx.method(mi), co))
                if co:
                    for pc, name, a, off in dx.disasm(co):
                        f.write('  %04x @%d: %s %s\n' % (pc, off, name, ', '.join(str(x) for x in a)))
