// eslint-disable-next-line
const CompressionPlugin = require('compression-webpack-plugin');

module.exports = {
  configureWebpack: {
    plugins: [new CompressionPlugin()]
  }
};
