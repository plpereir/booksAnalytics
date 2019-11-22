#!/bin/sh
dtstart=$(date '+%d/%m/%Y %H:%M:%S'); 
echo "starting json Details books informations files download $dtstart" 

filename="/Users/plpereir/Downloads/jsonFileList.txt"

while read -r line; do
    name="$line"
    filenameDetails="/Users/plpereir/Downloads/$name"

    while read -r lineDetails; do
        nameDetails="$lineDetails"
        if [[ $nameDetails == *'"id":'* ]]; ##note the space after the string you are searching for
        then
            
            curl --progress-bar -o /Users/plpereir/Downloads/booksID"${nameDetails:7:12}".html "https://books.google.com.br/books?id=${nameDetails:7:12}&sitesec=buy&hl=pt-BR&source=gbs_vpt_read"
            sleep 0
            echo "donwload file: booksID"${nameDetails:7:12}".html"
        fi   

    done < "$filenameDetails"

done < "$filename"

dtfinish=$(date '+%d/%m/%Y %H:%M:%S');
echo "finish json files Details books informations download $dtfinish"  