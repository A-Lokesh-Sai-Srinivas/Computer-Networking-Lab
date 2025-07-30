data = input("Enter the data: ")
fb = '0' + '1'*6 + '0'

print("Original given data: " , data)

def bitStuff(data) :
    return data.replace('1' * 5 , '1' * 5 +'0')
    

def bitDestuff(data) :
    return data.replace('1' * 5 + '0', '1' * 5)


sd = bitStuff(data)
print("Data after bit stuffing: " , sd)

dd = bitDestuff(sd)
print("Data after bit destuffing: ", dd)

