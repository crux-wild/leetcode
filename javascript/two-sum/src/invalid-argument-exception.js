/**
 * 用户输入的参数无法返回一个解
 * @class
 */
class InvalidArgumentException extends Error {
  constructor(message = 'No two sum solution') {
    super(message);
  }
}

export default InvalidArgumentException;
