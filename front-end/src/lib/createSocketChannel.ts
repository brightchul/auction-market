import { eventChannel  } from 'redux-saga';


export function createSocketChannel(url: string, socket: any) {
  return eventChannel(emit => {
    socket.subscribe(url, (payload: any) => {
      emit(payload);
    });
    const unsubscribe = () => {
      socket.onmessage = null;
    };
    return unsubscribe;
  });
};

export function closeChannel(channel: any) {
  if(channel) channel.close();
};