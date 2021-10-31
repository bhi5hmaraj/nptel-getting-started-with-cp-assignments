def rec(s):
    if len(s) <= 1:
        return 0
    elif s[:2] == '01' or s[:2] == '10':
        return 1 + rec(s[2:])
    else:
        return rec(s[1:])

t = int(input())
for _ in range(t):
    s = input().strip()
    z = len(list(filter(lambda ch: ch == '0', s)))
    o = len(s) - z
    can_remove = rec(s)
    # print("can remove = ", can_remove) 
    ans = 1 if o > z - can_remove else 0 if o < z - can_remove else -1
    print(ans)
    
