terms = []
with open('terms.txt') as f:
    for line in f:
        terms.append(line.rstrip())

with open("formatted.txt", "w") as f2:
    for t in terms:
        f2.write("[" + t + "]() <br>\n")