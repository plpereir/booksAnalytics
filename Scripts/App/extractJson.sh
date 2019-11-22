#!/bin/sh
export LANG=C.UTF-8;
dtstart=$(date '+%d/%m/%Y %H:%M:%S'); 
echo "starting json files download $dtstart" 
#max=5
#for i in `seq 1 $max`
#do
#    curl --progress-bar -o /Users/plpereir/Downloads/books_a_"$i".json https://www.googleapis.com/books/v1/volumes?q=a&startIndex="${i}"&orderBy=relevance&maxResults=40&printType=books&langRestrict=pt
#    sleep 1
#done
echo "starting search letter a"
filename="/Users/plpereir/Downloads/urllist_a.txt"
cont=0
while read -r line; do
    name="$line"
    curl --progress-bar -o /Users/plpereir/Downloads/books_a_"$cont".json $name
    sleep 10
        value=`cat /Users/plpereir/Downloads/books_a_"$cont".json`
    if [[ $value == *"error"* ]]; ##note the space after the string you are searching for
        then
            break;
        else
        if [[ $value == *'totalItems": 0'* ]]; ##note the space after the string you are searching for
            then
                break;
            else
                echo "donwload file: books_a_"$cont".json"
        fi   
    fi   

    cont=$((cont + 1))
done < "$filename"

echo "starting search letter e"
filename="/Users/plpereir/Downloads/urllist_e.txt"
cont=0
while read -r line; do
    name="$line"
    curl --progress-bar -o /Users/plpereir/Downloads/books_e_"$cont".json $name
    sleep 10
        value=`cat /Users/plpereir/Downloads/books_e_"$cont".json`
    if [[ $value == *"error"* ]]; ##note the space after the string you are searching for
        then
            break;
        else
        if [[ $value == *'totalItems": 0'* ]]; ##note the space after the string you are searching for
            then
                break;
            else
                echo "donwload file: books_e_"$cont".json"
        fi   
    fi   

    cont=$((cont + 1))
done < "$filename"

echo "starting search letter i"
filename="/Users/plpereir/Downloads/urllist_i.txt"
cont=0
while read -r line; do
    name="$line"
    curl --progress-bar -o /Users/plpereir/Downloads/books_i_"$cont".json $name
    sleep 10
        value=`cat /Users/plpereir/Downloads/books_i_"$cont".json`
    if [[ $value == *"error"* ]]; ##note the space after the string you are searching for
        then
            break;
        else
        if [[ $value == *'totalItems": 0'* ]]; ##note the space after the string you are searching for
            then
                break;
            else
                echo "donwload file: books_i_"$cont".json"
        fi   
    fi   

    cont=$((cont + 1))
done < "$filename"

echo "starting search letter o"
filename="/Users/plpereir/Downloads/urllist_o.txt"
cont=0
while read -r line; do
    name="$line"
    curl --progress-bar -o /Users/plpereir/Downloads/books_o_"$cont".json $name
    sleep 10
        value=`cat /Users/plpereir/Downloads/books_o_"$cont".json`
    if [[ $value == *"error"* ]]; ##note the space after the string you are searching for
        then
            break;
        else
        if [[ $value == *'totalItems": 0'* ]]; ##note the space after the string you are searching for
            then
                break;
            else
                echo "donwload file: books_o_"$cont".json"
        fi   
    fi   

    cont=$((cont + 1))
done < "$filename"

echo "starting search letter u"
filename="/Users/plpereir/Downloads/urllist_u.txt"
cont=0
while read -r line; do
    name="$line"
    curl --progress-bar -o /Users/plpereir/Downloads/books_u_"$cont".json $name
    sleep 10
        value=`cat /Users/plpereir/Downloads/books_u_"$cont".json`
    if [[ $value == *"error"* ]]; ##note the space after the string you are searching for
        then
            break;
        else
        if [[ $value == *'totalItems": 0'* ]]; ##note the space after the string you are searching for
            then
                break;
            else
                echo "donwload file: books_u_"$cont".json"
        fi   
    fi   

    cont=$((cont + 1))
done < "$filename"

echo "generate list Json Files"
ls  books*.json > jsonFileList.txt
chmod 777 jsonFileList.txt

dtfinish=$(date '+%d/%m/%Y %H:%M:%S');
echo "finish json files download $dtfinish" 

echo "start extract html details from books"
./extractJsonDetails.sh
