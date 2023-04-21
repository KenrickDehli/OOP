import os

VERTICAL_LINE = chr(9474)
HORIZONTAL_LINE = chr(9472)
RIGTH_ANGLE_LINE = chr(9492)
HORIZONTAL_VERTICAL_LINE = chr(9500)
TAB = chr(0x20) * 2
SPACE = chr(0x20)
drawVerticalLine = []


def print_directory(path, indentation_level=0):
    directories, files = (0, 0)
    directory = sorted(os.scandir(path), key=lambda f: f.name.lower())
    count = 1
    ending = directory.__len__()
    for item in directory:
        if (item.is_dir()):
            directories = directories + 1
            if (count == ending):
                drawVerticalLine.insert(indentation_level, False)
                print(calculateBase(indentation_level) +
                      RIGTH_ANGLE_LINE +
                      HORIZONTAL_LINE + item.name)
            else:
                print(calculateBase(indentation_level) +
                      HORIZONTAL_VERTICAL_LINE +
                      HORIZONTAL_LINE + item.name)
                drawVerticalLine.insert(indentation_level, True)
            x, y = print_directory(item.path, indentation_level + 1)
            directories = directories + x
            files = files + y
        elif (count == ending):
            files = files + 1
            print(calculateBase(indentation_level) +
                  RIGTH_ANGLE_LINE +
                  HORIZONTAL_LINE + item.name)
        else:
            files = files + 1
            print(calculateBase(indentation_level) +
                  HORIZONTAL_VERTICAL_LINE +
                  HORIZONTAL_LINE + item.name)
        count += 1
    return directories, files


def calculateBase(indentation_level):
    result = ""
    if indentation_level <= 0:
        return result
    for i in range(0, indentation_level):
        if (drawVerticalLine[i]):
            result = result + VERTICAL_LINE + TAB
        else:
            result = result + SPACE + TAB
    return result


directories, files = print_directory(".")
print()
print(directories, "directories, ", files, "files")
