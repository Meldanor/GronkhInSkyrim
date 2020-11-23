# GronkhSkyrim

Java15 Gradle application to parse [Gronkhs](https://www.youtube.com/user/Gronkh) Skyrim videos and extract statistics. 
It uses Tesseract for text recognition.

It also contains a simple Vu3 frontend to visualize the output.

## Status

This is more a prototype to be run local by me. It is not for general usage, but
you can look at the source code.

## Installation

You will need the following tools:
- Docker
- Java15
- FFMPEG
- Tessaract with German language files
- Yarn and Node


You will also need the videos.

## Usage

### Parser (Java)
You have to fiddle a bit in `coalMine/src/main/java/de/meldanor/gronkskyrim/Application.java`. It
supports two modes: `-t POSTPROCESS` and `-t ANALYZE`. The first will need a finished series event log, the second will
generate one based on the videos.

You have to set the file paths in the `coalMine/src/main/java/de/meldanor/gronkskyrim/Config.java` to your correct ones.
 
### Frontend
See the `frontend/package.json` for instructions. Start a local server with `yarn server` , after installing 
`yarn install` the packages.

## Contributing

You can fork it and create PRs, but please consider, this was a learning project for me and not a full running app.

## License

[MIT](https://choosealicense.com/licenses/mit/)
