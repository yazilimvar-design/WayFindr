import sys, os, collections
sys.path.insert(0,'/home/claude/tools')
from dex import Dex
def strs(dexdir):
    out=collections.defaultdict(set)
    files=sorted([f for f in os.listdir(dexdir) if f.endswith('.dex')], key=lambda f:(len(f),f))
    seen=set()
    for f in files:
        d=Dex(os.path.join(dexdir,f))
        for c,cd in d.classes():
            if not c.startswith('Lcom/example/wayfindr/') or c in seen: continue
            seen.add(c)
            top=c.split('$')[0].rstrip(';').replace('/ComposableSingletons','/')
            for mi,af,co in d.methods_of(cd):
                if not co: continue
                try:
                    for _,n,a,_ in d.disasm(co):
                        if n.startswith('const-string'): out[top].add(a[1])
                except Exception: pass
    return out
a=strs(sys.argv[1]); b=strs(sys.argv[2])
for k in sorted(set(a)|set(b)):
    add=sorted(b.get(k,set())-a.get(k,set())); rem=sorted(a.get(k,set())-b.get(k,set()))
    if add or rem:
        print('##',k.replace('Lcom/example/wayfindr/',''))
        for s in add: print('  +',s[:160])
        for s in rem: print('  -',s[:160])
