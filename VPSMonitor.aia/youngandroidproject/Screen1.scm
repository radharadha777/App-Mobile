{
  "properties": {
    "AppName": "VPS Monitor",
    "Title": "VPS Monitor",
    "ShowStatusBar": true
  },
  "components": [
    {"type":"TextBox","name":"txtIp","properties":{"Hint":"Enter VPS IP"}},
    {"type":"TextBox","name":"txtPort","properties":{"Hint":"Enter Port","Text":"5000"}},
    {"type":"TextBox","name":"txtPassword","properties":{"Hint":"Enter Password","PasswordVisible":false}},
    {"type":"Button","name":"btnSave","properties":{"Text":"Save Settings"}},
    {"type":"Button","name":"btnFetch","properties":{"Text":"Fetch Metrics"}},
    {"type":"Label","name":"lblCPU","properties":{"Text":"CPU:"}},
    {"type":"Label","name":"lblRAM","properties":{"Text":"RAM:"}},
    {"type":"Label","name":"lblDisk","properties":{"Text":"Disk:"}},
    {"type":"Label","name":"lblContainers","properties":{"Text":"Containers:"}},
    {"type":"Web","name":"Web1"},
    {"type":"TinyDB","name":"TinyDB1"}
  ]
}
