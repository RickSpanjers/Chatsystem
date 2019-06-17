function processMessage(msg) {
    let message = {operationMessage: "", data: ""};
    message = JSON.parse(msg.data);
    handleMessage(message.operationMessage, message.data);
}
