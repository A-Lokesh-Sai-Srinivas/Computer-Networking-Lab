def input_binary(prompt):
    
    while True:
        data = input(prompt).strip()
        if all(c in '01' for c in data) and len(data) > 0:
            return data
        else:
            print("Please enter a valid binary string (only 0 and 1).")

def mod2div(dividend, divisor):
    
    divisor_len = divisor.bit_length()
    dividend_len = dividend.bit_length()

    shift = dividend_len - divisor_len
    while shift >= 0:
        dividend ^= (divisor << shift)
        dividend_len = dividend.bit_length()
        shift = dividend_len - divisor_len
    return dividend  # remainder

def sender(data, generator):
    
    n = len(generator) - 1
    intData = int(data, 2)
    intGenerator = int(generator, 2)

    # Append zeros (shift left)
    data_appended = intData << n

    remainder = mod2div(data_appended, intGenerator)

    transmitted_data = (intData << n) | remainder

    print(f"Remainder we got (CRC bits): {bin(remainder)[2:].zfill(n)}")
    print(f"Data sent by sender         : {bin(transmitted_data)[2:]}")
    return transmitted_data, intGenerator, n

def receiver(received_data, intGenerator, n):
    remainder = mod2div(received_data, intGenerator)
    print(f"Remainder at receiver: {bin(remainder)[2:].zfill(n)}")
    if remainder == 0:
        print("Data is accepted (No error detected)")
    else:
        print("Data is rejected (Error detected)")

def main():
    end = '\n' *3
    print(end,"\t\t", "-"*5, " Sender Side ", "-"*5, end= end)
    data = input_binary("Enter the data to be transmitted (binary): ")
    generator = input_binary("Enter the generator (binary): ")
    
    print(f"Given data     : {data}")
    print(f"Given generator: {generator}\n")
    
    transmitted_data, intGenerator, n = sender(data, generator)

    print(end,"\n\t\t", "-"*5, " Receiver Side ", "-"*5, end= end)
    print(f"Data received by receiver: {bin(transmitted_data)[2:]}")
    print(f"Generator received       : {generator}")

    receiver(transmitted_data, intGenerator, n)

if __name__ == "__main__":
    main()

