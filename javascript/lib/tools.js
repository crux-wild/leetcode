/**
 * @param {String} filename -单元测试的文件的路径
 * @return {Object} 对应源文件所包含的默认模块
 * @function
 */
function getSourceFile(filename = '') {
  const sourceFile = filename.replace(/\/spec\//, '/src/')
    .replace(/\.spec.js/, '.js');

  const module = require(sourceFile).default;

  return module;
}

export {
  getSourceFile as default,
};
