import sys

example_text = '''I know that virtue to be in you, Brutus, As well as I do know your outward favour. Well, ho-
nour is the subject of my story. I cannot tell what you and other men Think of this life; but,
for my single self, I had as lief not be as live to be In awe of such a thing as I myself. I was
born free as Caesar; so were you: We both have fed as well, and we can both Endure the
winter's cold as well as he: For once, upon a raw and gusty day, The troubled Tiber chafing
with her shores, Caesar said to me 'Darest thou, Cassius, now Leap in with me into this an-
gry flood, And swim to yonder point?' Upon the word, Accoutred as I was, I plunged in And
bade him follow; so indeed he did. The torrent roar'd, and we did buffet it With lusty sinews,
throwing it aside And stemming it with hearts of controversy; But ere we could arrive the
point proposed, Caesar cried 'Help me, Cassius, or I sink!' I, as Aeneas, our great ancestor,
Did from the flames of Troy upon his shoulder The old Anchises bear, so from the waves of
Tiber Did I the tired Caesar. And this man Is now become a god, and Cassius is A wretched
creature and must bend his body, If Caesar carelessly but nod on him. He had a fever when
he was in Spain, And when the fit was on him, I did mark How he did shake: 'tis true, this
god did shake; His coward lips did from their colour fly, And that same eye whose bend doth
awe the world Did lose his lustre: I did hear him groan: Ay, and that tongue of his that bade the Romans Mark him and write his speeches in their books, Alas, it cried 'Give me some
drink, Titinius,' As a sick girl. Ye gods, it doth amaze me A man of such a feeble temper
should So get the start of the majestic world And bear the palm alone.'''

encoded_text = '''
Reu jf zk zj. Wfi kyzj kzdv Z nzcc cvrmv pfl: Kf-dfiifn, zw pfl gcvrjv kf jgvrb nzky dv, Z nzcc
tfdv yfdv kf pfl; fi, zw pfl nzcc, Tfdv yfdv kf dv, reu Z nzcc nrzk wfi pfl.
'''


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


def calulateAscii(key: int, value: int, is_upper: bool):
    val = (int(key) + value) % 26
    if (is_upper):
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
    sumOfWords = 0
    for k, v in histogram.items():
        sumOfWords = sumOfWords + v
    probability = []
    for i in range(97, 123):
        if chr(i) in histogram:
            probability.append(histogram[chr(i)]/sumOfWords)
        else:
            probability.append(0)
    return probability


def crack_caesar(exampletext: str, text: str):
    freqencies_exampleText = frequencies(string_histrogram(exampletext))
    smallest_vector = 100
    smallest_key = 0
    for i in range(1, 27):
        encoded_text = encode_text(text, i)
        encoded_text_histogram = string_histrogram(encoded_text)
        frequencies_encoded_text = frequencies(encoded_text_histogram)
        frequencies_vector = compare_frequencies(freqencies_exampleText, frequencies_encoded_text)
        if frequencies_vector < smallest_vector:
            smallest_vector = frequencies_vector
            smallest_key = i
    return encode_text(text, smallest_key)


def compare_frequencies(frequencies_example_text: str, frequencies_encoded_text: str):
    sum = 0
    for i in range(0, 26):
        numerator = pow((frequencies_encoded_text[i] - frequencies_example_text[i]), 2)
        denominator = frequencies_example_text[i]
        if denominator == 0:
            continue
        sum = sum + (numerator / denominator)
    return sum


def __main__():
    if (sys.argv.__len__() > 2 and sys.argv.__len__() <= 4):
        try:
            text = sys.argv[1]
            key = eval(sys.argv[2])
            encryption = encode_text(text, key)
            print("Ihr Text verschlüsselt:", encryption)
            print()
            # Ergebnis für b)
            histogram = string_histrogram(text)
            print("Das Histogramm für Ihren Text:", histogram)
            print()
            # Ergebnis für c)
            frequencies_text = frequencies(histogram)
            print("Der Wahrscheinlickeitsvektor für Ihren Text: \n", frequencies_text)
            # Ergebnis für d)
            print()
            print("Der zu entschlüssenlde Text:", encoded_text)
            decoded_text = crack_caesar(example_text, encoded_text)
            print("Der entschlüsselte Text:", decoded_text)
        except Exception as ex:
            print("Something went wrong:", ex)
    else:
        print('''Script stopped because the input parameters were greater than or less than 2''')


__main__()
