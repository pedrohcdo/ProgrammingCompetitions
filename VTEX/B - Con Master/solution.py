# Pedro H.
# <pedrohcd@hotmail.com>
def compute(size):
    if size <= 1:
        return c

    map = [None for _ in range(size + 1)]
    map[0] = 0
    map[1] = c

    balance = 1

    def computeFor(k):
        return c + map[balance - 1] - a * (balance - 1) + map[k - balance] - b * (k - balance)

    for i in range(2, size + 1):
        map[i] = computeFor(i)
        if balance + 1 <= i:
            balance += 1
            top = computeFor(i)
            if top > map[i]:
                map[i] = top
            else: balance-=1

    return map[size]

def gcd(a, b):
    while b != 0:
        t = a
        a = b
        b = t % b
    return a

##
inp = input().split(" ")
k = int(inp[0])
a = int(inp[1])
b = int(inp[2])
c = int(inp[3])

##
sum = compute(k)
_gcd = gcd(sum, k)
print("%d/%d" % (int(sum/_gcd), int(k/_gcd)))