FROM gradle:6.7-jdk15

RUN apt update && apt install -y \
                  tesseract-ocr \
                  tesseract-ocr-deu \
                  ffmpeg

# Override the data from the repository with the "best" trained model
RUN wget \
    -O /usr/share/tesseract-ocr/4.00/tessdata/deu.traineddata \
    https://github.com/tesseract-ocr/tessdata_best/raw/master/deu.traineddata

WORKDIR /app

CMD tail -f /dev/null
