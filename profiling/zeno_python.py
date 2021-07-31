import math, time
 
def test100K(len, n_loop, val, c):
    z=0
    array=[x for x in range(len)]
    begin = time.time()
    for j in range(n_loop):
        for i in range(len):
            if array[i] != math.floor((i+1)*val/c):
               z += 1
    end = time.time()
    secs = (end - begin)
    print("100K if, 100times: {:.4f} secs z={}".format(secs, z))

def test100K_modified(len, n_loop, val, c):
    z=0
    array=list(range(len))
    begin = time.time()
    for j in range(n_loop):
        for i in range(len):
            if array[i] != (((i+1)*val)//c):
               z += 1
    end = time.time()
    secs = (end - begin)
    print("100K if, 100times: {:.4f} secs z={}".format(secs, z))

test100K(100_000, 100,-586, 99)
test100K_modified(100_000, 100, -586, 99)

