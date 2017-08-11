/**
 * 用户输入的参数无法返回一个解
 * @class
 */
class IllegalArgumentException extends Error {
  constructor(message = 'One or more argument is illegal;') {
    super(message);
  }
}

export default IllegalArgumentException;
