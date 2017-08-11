/**
 * 执行方法时，集合为空
 * @class
 */
class NullSetException extends Error {
  constructor(message = 'Current section is a Null set;') {
    super(message);
  }
}

export default NullSetException;
