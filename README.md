# MagicPaper语义

## 计时器控制

### 实现

- foliaTimer()
  - 创建folia计时器
- paperTimer()
  - 创建paper计时器
- addToTimer()
  - 给计时器添加魔咒任务

### 参数格式

- foliaTimer(计时器名 延迟开启 执行间隔 数据上下文)
- paperTimer((计时器名 延迟开启 执行间隔 数据上下文)
- addToTimer(计时器名 魔咒)

### 返回值

- null

## 通用对象

### 实现

- player()
  - 玩家对象
- location()
  - 坐标对象

### 参数格式

- player()
  - player()
    - 空参数默认返回魔咒执行者
  - player(玩家名字符串)
    - 根据玩家名获得玩家对象
  - player(object类型玩家)
    - 将object还原为player
  - player(坐标  半径 数量)
    - 根据坐标获得半径内指定数量的玩家
- location()
  - location()
    - 空参取魔咒执行者的坐标
  - location(玩家)
    - 获取玩家坐标
  - location(object对象)
    - 将object还原为location
  - location(x y z yaw pitch world)
    - 根据具体参数生成坐标

### 返回值

- player()
  - 按坐标寻找玩家时找到的目标是玩家列表
  - 其余返回单个玩家对象
- location()
  - 坐标对象

## 行为

### 实现

- consoleCommand()
  - 控制台执行指令
- playerCommand()
  - 玩家执行指令
- playerAsyncTeleport()
  - 玩家异步传送
- playerTeleport()
  - 玩家传送
- senderToAllPlayer()
  - 给在线玩家发送消息
- senderToPlayer()
  - 给某个玩家发消息

### 参数格式

- consoleCommand(字符串指令)
- playerCommand(玩家 字符串指令)
- playerAsyncTeleport(玩家 坐标)
- playerTeleport(玩家 坐标)
- senderToAllPlayer(字符串内容)
- senderToPlayer(玩家 字符串内容)

### 返回值

- null

## HOOK

### luckperms

- 实现
  - playerLPMetaSet()
    - 设置玩家的Meta键值
  - playerLPTempMetaSet()
    - 设置玩家的临时Meta键值
  - playerLPMetaGet()
    - 获取玩家的某个键的值
- 参数格式
  - playerLPMetaSet(玩家 键 值)
  - playerLPTempMetaSet
    (玩家 键 值 时间(长整型))
  - playerLPMetaGet(玩家 键)
- 返回值
  - playerLPMetaSet()
    - null
  - playerLPTempMetaSet()
    - null
  - playerLPMetaGet()
    - 字符串

### placeholderapi

- 实现
  - placeholderAPI()
    - 解析papi变量
- 参数格式
  - placeholderAPI(变量)
    - 不需要加%
- 返回值
  - 字符串

## 执行控制

### 实现

- foliaSpellAsyncExecute()
  - folia下的异步执行
- foliaSpellExecute()
  - folia下的同步执行
- paperSpellAsyncExecute()
  - paper下的异步执行
- paperSpellExecute()
  - paper下的同步执行

### 参数格式

- foliaSpellAsyncExecute(魔咒1 魔咒2...)
- foliaSpellExecute(魔咒1 魔咒2...)
- paperSpellAsyncExecute(魔咒1 魔咒2...)
- paperSpellExecute(魔咒1 魔咒2...)

### 返回值

- null

