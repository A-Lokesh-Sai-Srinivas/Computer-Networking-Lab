
data = input("Enter the data: ")
fb = input("Enter the flag bit: ")
eb = input("Enter the esc bit: ")

print("Entered data : " , data)

def charStuff(data):
    x = data.replace(eb , eb * 2)
    y = x.replace(fb , eb + fb)
    return fb+y+fb

def charDestuff(data):
    x = data.replace(eb * 2 , eb)
    y = x.replace(eb + fb , fb)
    return y[1:-1]

sd = charStuff(data)
print("Data after charater stuffing :" ,sd)

dd = charDestuff(sd)
print("Data after charater destuffing: " , dd)


