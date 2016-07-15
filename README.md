The project is a bash (command interpreter) with the following commands:

make directory - mkdir nameOfFolder
traverse directory - ls depth
comparing files - cmp absolutePath1 absolutePath2
change directory - cdrel relativePath or ".." for level up
change directory - cdabs absolutePath
read students data base - readdb fileName
drop students data base - dropdb
filter students - filter {courseName} excellent/average/poor take 2/5/all
order students - order {courseName} ascending/descending take 20/10/all
show course – show {courseName} {studentName}
download file - download URL (saved in current directory)
download file on new thread - downloadAsynch URL (saved in the current directory)
open file – open {fileName}
get help – help
