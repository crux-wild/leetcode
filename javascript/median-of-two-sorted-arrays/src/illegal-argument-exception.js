/**
 * 调用输入的参数不正确
 * @class
 */
class IllegalArgumentException extends Error {
  constructor(message = 'One or more argument is illegal;') {
    super(message);
  }
}

export default IllegalArgumentException;
