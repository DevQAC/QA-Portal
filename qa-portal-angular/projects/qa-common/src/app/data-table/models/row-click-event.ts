export interface IRowClickEvent<T = any> {
  index: number;
  data: T;
  event: MouseEvent | KeyboardEvent;
}
