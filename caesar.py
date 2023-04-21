import sys


def encode_text(text: str, key: int):
    result = ""
    for char in text:
        value = ord(char)
        if (char.isupper()):
            encoded = calulateAscii(key, value - 65, True)
            result = result + chr(encoded)
        elif (char.islower()):
            encoded = calulateAscii(key, value - 97, False)
            result = result + chr(encoded)
        else:
            result = result + char
    return result


def calulateAscii(key, value, isupper):
    val = (int(key) + value) % 26
    if (isupper):
        return val + 65
    else:
        return val + 97


def string_histrogram(text: str):
    frequencies = {}
    for char in text.lower():
        if (char.isalpha()):
            frequencies[char] = 0

    for char in text.lower():
        if (char.isalpha()):
            frequencies[char] += 1
    return frequencies


def frequencies(histogram: dict):
    alphabet = {}
    for i in range(97, 123):
        alphabet[chr(i)] = 0
    for element in histogram:
        pass
    print(alphabet)


def __main__():
    if (sys.argv.__len__() > 1 and sys.argv.__len__() <= 3):
        text = sys.argv[1]
        key = sys.argv[2]
        encryption = encode_text(text, key)
        print(text)
        print(encryption)
        histo = string_histrogram(text)
        print(histo)
        frequencies(histo)
    else:
        print("Script stopped because the input parameters were greater \
              than or less than 2")


__main__()
