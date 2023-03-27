import os

VERTICAL_LINE = chr(9472)
HORIZONTAL_LINE = chr(9474)
RIGTH_ANGLE = chr(9492)
HORIZONTAL_VERTICAL_LINE = chr(9500)
TAB = chr(0x20) + chr(0x20)


def print_directory(path, indentation_level=0):
    directory = sorted(os.scandir(path), key=lambda f: f.name.lower())
    count = 1
    ending = directory.__len__()
    for item in directory:
        if (item.is_dir()):
            print((indentation_level * HORIZONTAL_LINE) +
                  indentation_level * TAB + RIGTH_ANGLE +
                  VERTICAL_LINE + " " + item.name)
            level = indentation_level + 1
            print_directory(item.path, level)
        elif (count == ending):
            print((indentation_level * HORIZONTAL_LINE) +
                  (indentation_level * TAB) + RIGTH_ANGLE +
                  VERTICAL_LINE + " " + item.name)
        else:
            print((indentation_level * HORIZONTAL_LINE) +
                  indentation_level * TAB + HORIZONTAL_VERTICAL_LINE +
                  VERTICAL_LINE + " " + item.name)
            count += 1


print_directory(".")
