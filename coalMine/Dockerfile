FROM gradle:6.7-jdk15

RUN apt update && apt install -y \
                  tesseract-ocr \
                  tesseract-ocr-deu \
                  ffmpeg

WORKDIR /app

CMD tail -f /dev/null
