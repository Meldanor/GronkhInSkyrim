# Script that will extract the frames using ffmpeg
# Needs the input directory containg all videos as first parameter
#!/bin/bash
FILES=$1/*
ITER=0
for f in $FILES; do
  ((ITER++))
  n=`printf %03d $ITER`
  echo "Processing $n $f file..."
  mkdir -p "/output/$n"
  ffmpeg -i "$f" -r 2 /output/$n/%08d.png
done
