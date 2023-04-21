import os

VERTICAL_LINE = chr(9472)
HORIZONTAL_LINE = chr(9474)
RIGTH_ANGLE = chr(9492)
HORIZONTAL_VERTICAL_LINE = chr(9500)
TAB = chr(0x20) + chr(0x20)
SPACE = chr(0x20)


def print_directory(path, indentation_level=0):
    directory = sorted(os.scandir(path), key=lambda f: f.name.lower())
    count = 1
    ending = directory.__len__()
    for item in directory:
        if (item.is_dir()):
            if (count == ending):
                print(calculateBase(indentation_level, True, True) +
                      RIGTH_ANGLE +
                      VERTICAL_LINE + item.name)
            else:
                print(calculateBase(indentation_level, False, True) +
                      HORIZONTAL_VERTICAL_LINE +
                      VERTICAL_LINE + item.name)
            print_directory(item.path, indentation_level + 1)
        elif (count == ending):
            print(calculateBase(indentation_level, True, False) +
                  RIGTH_ANGLE +
                  VERTICAL_LINE + item.name)
        else:
            print(calculateBase(indentation_level, False, False) +
                  HORIZONTAL_VERTICAL_LINE +
                  VERTICAL_LINE + item.name)
        count += 1


def calculateBase(indentation_level, isLastItem, isDirectory):
    result = ""
    if indentation_level <= 0:
        return result
    for i in range(0, indentation_level):
        result = result + HORIZONTAL_LINE + TAB
    return result


print_directory(".")
