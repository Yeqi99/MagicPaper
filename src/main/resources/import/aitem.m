将传入的参数解包为arg1 arg2 ...的格式
unpack(args)

arg1:玩家
arg2:材质名
arg3:显示名

新建一个物品 传入第二个参数为物品材质名 创建别名item 之后使用item即可指代此物品
vdef(item newItem(arg2))

设置物品的显示名为第三个参数 拥有特殊符号的字符串必须使用str方法包裹以合成完整的字符串
itemSetName(item str(&4 arg3))

将制作好的物品发送给传入的第一个参数 第一个参数是玩家
itemGivePlayer(arg1 item)

使用此方法时必须严格按照 玩家 材质名 显示名 的顺序传入参数

