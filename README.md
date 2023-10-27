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

## 技能

### 实现

- jumpSkill()
  - 向面朝方向跳跃
- jumpToLocationSkill()
  - 向指定坐标方向跳跃

### 参数格式

- jumpSkill(玩家 距离力度 高度力度)
- jumpToLocationSkill(玩家 目标坐标 速度 高度力度)

### 返回值

- jumpSkill()
  - null
- jumpToLocationSkill()
  - null

## 冷却

### 实现

- addCoolDown()
  - 新建冷却
- checkCoolDown()
  - 检查冷却
- gocd()
  - 魔咒的cd操作
- getcd()
  - 获得冷却时间

### 参数格式

- addCoolDown(id long(持续时间) 冷却缩减 固定冷却缩减)
- checkCoolDown(id)
- gocd(冷却ID 冷却时长 冷却中提示[可选])
- getcd(冷却ID)

### 返回值

- addCoolDown()
  - null
- checkCoolDown()
  - bool
- gocd()
  - null
- getcd()
  - str

## 通用对象

### 实现

- player()
  - 玩家对象
- location()
  - 坐标对象
- item()
  - 物品对象
- citem()
  - 物品克隆副本
- entity()
  - 生物
- bossbar()
  - boss血条
- magicLocation()
  - 优化坐标对象创建
  - 参数需求全部为字符串,无需转换

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
- item()
  - item(玩家 槽位代号)
    - mh
      - 主手
    - oh
      - 副手
    - h
      - 头盔
    - c
      - 胸甲
    - l
      - 腿甲
    - s
      - 靴子
  - item(玩家 槽位序号)
- cloneItem()
  - 与item()相同
- entity(玩家)
- bossbar(key title color style)
  - 颜色
    - https://hub.spigotmc.org/javadocs/spigot/org/bukkit/boss/BarColor.html
  - 风格
    - https://hub.spigotmc.org/javadocs/spigot/org/bukkit/boss/BarStyle.html
- magicLocation()
  - magicLocation(x y z world)
  - magicLocation(x y z world yaw patch)

### 返回值

- player()
  - 按坐标寻找玩家时找到的目标是玩家列表
  - 其余返回单个玩家对象
- location()
  - 坐标对象
- item()
  - 物品本身
- cloneItem()
  - 物品副本
- entity()
  - 实体
- bossbar()
  - bossbar
- magicLocation()
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
- updateInventory()
  - 刷新背包物品信息
- actionMsg()
  - 发送行动条信息
- playerCloseInv()
  - 关闭背包
- potionAdd()
  - 增加药水效果

### 参数格式

- consoleCommand(字符串指令)
- playerCommand(玩家 字符串指令)
- playerAsyncTeleport(玩家 坐标)
- playerTeleport(玩家 坐标)
- senderToAllPlayer(字符串内容)
- senderToPlayer(玩家 字符串内容)
- updateInventory(玩家)
- actionMsg(玩家 消息)
- playerCloseInv(玩家)
- potionAdd(玩家 药水类型 药水等级 持续时间)
  - https://hub.spigotmc.org/javadocs/spigot/org/bukkit/potion/PotionEffectType.html

### 返回值

- null

## Buff

### 实现

- buffAdd()
  - 给某个目标添加某个buff的剩余时间
- buffTimeGet()
  - 获取某个目标的某个buff剩余时间

### 参数格式

- buffAdd(目标ID BuffID 持续时间 附加属性字符串[可选])
- buffTimeGet(目标ID BuffID)

### 返回值

- buffAdd()
  - null
- buffTimeGet()
  - str类型的数字

## HOOK

### luckperms

- 实现
  - playerLPMetaSet()
    - 设置玩家的Meta键值
  - playerLPTempMetaSet()
    - 设置玩家的临时Meta键值
  - playerLPMetaGet()
    - 获取玩家的某个键的值
  - playerHasPermission()
    - 检查玩家是否有权限
- 参数格式
  - playerLPMetaSet(玩家 键 值)
  - playerLPTempMetaSet
    (玩家 键 值 时间(长整型))
  - playerLPMetaGet(玩家 键)
  - playerHasPermission(玩家 权限)
- 返回值
  - playerLPMetaSet()
    - null
  - playerLPTempMetaSet()
    - null
  - playerLPMetaGet()
    - 字符串
  - playerHasPermission()
    - bool

### placeholderapi

- 实现
  - placeholderAPI()
    - 解析papi变量
  - papiStr()
    - 解析整个字符串
- 参数格式
  - placeholderAPI(变量)
    - 不需要加%
  - papiStr(字符串)
- 返回值
  - 字符串

### abolethplus

- 实现
  - aboAdd()
    - 使用添加abo数据
  - aboGet()
    - 获取abo数据
  - aboGetUser()
    - 使用abo获取玩家UUID
  - aboSet()
    - 设置abo数据
  - aboEdit()
    - 编辑数据
  - aboSetTime()
    - 设置变量持续时间
  - aboAddItem()
    - 使用abo存储物品
  - aboGetItem()
    - 使用abo获得物品
- 参数格式
  - aboAdd(uuid 键 值)
  - aboGet(uuid 键)
  - aboGetUser(玩家名)
  - aboSet(uuid 键 值)
  - aboEdit(uuid 键 值 action)
    - action
      - abo原生动作 参考官方文档
  - aboSetTime(uuid 键 long(时间))
  - aboAddItem(uuid 键 物品)
  - aboGetItem(uuid 键)
- 返回值
  - aboAdd()
    - null
  - aboGet()
    - str
  - aboUserGet()
    - str
  - aboSet()
    - null
  - aboEdit()
    - null
  - aboSetTime()
    - null
  - aboAddItem()
    - null
  - aboGetItem()
    - item

### mythicmobs

- 实现
  - castSkill()
    - 执行mm技能
  - spawnMob()
    - 生成mm怪物
- 参数格式
  - castSkill(玩家 技能名 威力系数)
  - spawnMob(怪物名 坐标)
- 返回值
  - castSkill()
    - null
  - spawnMob()
    - entity

### itemsadder

- 实现
  - IAItem()
    - 获得IA物品
  - placeIABlock()
    - 放置IA自定义方块
- 参数格式
  - IAItem(IA物品ID)
  - placeIABlock(IA方块ID 坐标)
- 返回值
  - IAItem()
    - item
  - placeIABlock()
    - bool

### vault

- 实现
  - vaultGet()
    - 获得余额
  - vaultGive()
    - 给余额
  - vaultHas()
    - 是否有足够的余额
  - vaultTake()
    - 扣除余额
- 参数格式
  - vaultGet(玩家)
  - vaultGive(玩家 金额数量)
  - vaultHas(玩家 金额数量)
  - vaultTake(玩家 金额数量)
- 返回值
  - vaultGet()
    - int
  - vaultGive()
    - null
  - vaultHas()
    - bool
  - vaultTake()
    - bool

### playerpoints

- 实现
  - playerpointsGet()
    - 获得余额
  - playerpointsGive()
    - 给余额
  - playerpointsHas()
    - 是否有足够的余额
  - playerpointsSet()
    - 设置余额
  - playerpointsTake()
    - 扣除余额
- 参数格式
  - playerpointsGet(玩家)
  - playerpointsGive(玩家 金额数量)
  - playerpointsHas(玩家 金额数量)
  - playerpointsSet(玩家 金额数量)
  - playerpointsTake(玩家 金额数量)
- 返回值
  - playerpointsGet()
    - double
  - playerpointsGive()
    - bool
  - playerpointsHas()
    - bool
  - playerpointsSet()
    - bool
  - playerpointsTake()
    - bool

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
- publicContextGet()
  - 获取公共数据上下文
- playerTraversal()
  - 遍历玩家列表

### 参数格式

- foliaSpellAsyncExecute(魔咒1 魔咒2...)
- foliaSpellExecute(魔咒1 魔咒2...)
- paperSpellAsyncExecute(魔咒1 魔咒2...)
- paperSpellExecute(魔咒1 魔咒2...)
- publicContextGet()
- playerTraversal(players player i)

### 返回值

- null

## 触发器

### 实现

- addSpellToTrigger()
  - 给某个触发器添加魔咒任务
- triggerClearSpell()
  - 清除某个触发器任务

### 参数格式

- addSpellToTrigger(触发器名 魔咒)
- triggerClearSpell(触发器名)

### 返回值

- null

## 信息

### 实现

- playerName()
  - 获取玩家名字
- paperConstants()
  - 提取配置文件常量
- stringComparison()
  - 字符串比较
- ignoreCaseStringComparison()
  - 无视大小写字符串比较
- color()
  - 颜色代码修正
- isTimeRange()
  - 判断是否在时间段内
- inLocationRange()
  - 判断坐标是否在区域内

### 参数格式

- playerName(玩家)
- paperConstants(键)
- stringComparison(字符串1 字符串2)
- ignoreCaseStringComparison(字符串1 字符串2)
- color(字符串)
- isTimeRange(时间段)
  - 8:30
  - 8:30-9:30
- inLocationRange(目标坐标 范围坐标1 范围坐标2)

### 返回值

- playerName()
  - str
- paperConstants()
  - str
- stringComparison()
  - bool
- ignoreCaseStringComparison()
  - bool
- color()
  - str
- isTimeRange()
  - bool
- inLocationRange()
  - bool

## 物品操作

### 实现

- itemGivePlayer()
  - 物品发送给玩家
- itemSetPlayer()
  - 设置玩家某个槽位的物品
- itemLoreAddLine()
  - 给某个玩家的某个槽位的物品添加一行lore
- itemLoreRemove()
  - 移除物品的某行NBT
- itemToString()
  - 物品转字符串
- stringToItem()
  - 字符串转物品
- itemNBTAdd()
  - 物品添加nbt
- itemNBTGet()
  - 获取物品的nbt值
- itemNBTRemove()
  - 移除物品的某个nbt
- itemGetName()
  - 获取物品名
- itemSetName()
  - 设置物品名
- itemTypeGet()
  - 获取物品材质名
- itemTypeSet()
  - 设置物品材质名
- itemLoreHasLineNoColor()
  - 忽略颜色查找是否有对应行内容
- itemLoreHasLine()
  - 查找是否有对应行内容
- itemVarParse()
  - 解析物品变量
- itemLoreGet()
  - 获得某行lore
- newItem()
  - 新建一个物品
- refreshVar()
  - 按照模板刷新lore显示
- itemModelGet()
  - 获取物品自定义模型值
- itemModelSet()
  - 设置物品自定义模型值
- itemEnchantmentGet()
  - 获得物品的附魔等级
- itemEnchantmentSet()
  - 设置物品附魔等级
- itemDamageSet()
  - 设置物品耐久损失值
- itemDamageGet()
  - 获得物品耐久损失值
- itemAmountSet()
  - 设置物品数量
- itemAmountGet()
  - 获得物品数量
- itemAttributeSet()
  - 设置物品属性
- itemAttributeGet()
  - 获得物品某个属性值
- itemFlag()
  - 更改显示标签
- itemEnableRefresh()
  - 允许MagicPaper对物品的lore自动解析
- itemFormatSet()
  - 设置当前物品的解析模板
- itemInfoSet()
  - 设置物品描述(nbt)
- itemInfoAdd()
  - 添加一行物品描述
- itemBoreSet()
  - 设置一个镶嵌孔
- itemSupportBoreAddressSet()
  - 让某个物品可以镶嵌到某个镶嵌孔
- addItemToBore()
  - 尝试将物品镶嵌到另一个物品的镶嵌孔
- removeItemFromBore()
  - 尝试从物品上拆下某个镶嵌物品
- itemAddSpell()
  - 给物品添加Spell触发
- mergeNBT()
  - 合并nbt

### 参数格式

- itemGivePlayer(玩家 物品)
- itemSetPlayer(玩家 物品 槽位)
  - 槽位详见item方法
- itemLoreAddLine(物品 文本)
- itemToString(物品)
- stringToItem(字符串)
- itemNBTAdd(物品 路径 键 值)
  - 根目录为/
  - 比如我要在根目录下TEST内存储数据
    则路径为/TEST
- itemNBTGet(物品 路径 键 值类型)
  - 类型
    - STRING
    - ITEMSTACK
    - INT
    - LONG
    - FLOAT
    - DOUBLE
    - UUID
    - BOOLEAN
- itemNBTRemove(物品 路径 键)
- itemGetName(物品)
- itemSetName(物品 物品名)
- itemTypeGet(物品)
- itemTypeSet(物品 材质名)
- itemLoreRemove(物品 行号)
- itemLoreHasLineNoColor(物品 行内容)
- itemLoreHasLine(物品 行内容)
- itemVarParse(物品 内容)
- itemLoreGet(物品 行号)
- newItem(材质)
- refreshVar(物品 模板ID)
  - 物品显示模板在插件配置文件夹item-format
- itemModelGet(物品)
- itemModelSet(物品 值)
- itemEnchantmentGet(物品 附魔名)
  - 附魔名规则
    - 默认是minecraft原版附魔
    - 使用格式 附魔所属:附魔名 指定其他模块提供的附魔
    - 原版附魔列表
      - https://hub.spigotmc.org/javadocs/spigot/org/bukkit/enchantments/Enchantment.html
- itemEnchantmentSet(物品 附魔名 等级)
- itemDamageSet(物品 值)
  - 设置物品已经消耗了多少耐久 而非还有多少耐久
  - 设置为-1时物品变得坚不可摧
- itemDamageGet(物品)
  - 坚不可摧的物品返回-1
- itemAmountSet(物品 值)
- itemAmountGet(物品)
- itemAttributeSet(物品 属性ID 属性名 值 生效位置 计算方式)
  - 生效位置
    - mh
      - 主手
    - oh
      - 副手
    - h
      - 头盔
    - c
      - 胸甲
    - l
      - 腿甲
    - s
      - 靴子
  - 计算方式
    - +
      - 直接加算
    - +%
      - 加一个比例
    - +*
      - 加一个系数
  - 属性名
    - https://hub.spigotmc.org/javadocs/spigot/org/bukkit/attribute/Attribute.html
- itemAttributeGet(物品 属性ID 属性名 生效位置 计算方式)
- itemFlag(物品 标签代号)
  - 代号意义
    - 1 - 隐藏附魔
    - 2 - 隐藏自定义属性
    - 3 - 隐藏附魔和自定义属性
    - 4 - 隐藏{Unbreakable} (永久不毁)
    - 8 - 隐藏{CanDestroy} (可破坏)
    - 16 - 隐藏{CanPlaceOn} (可放置在)
    - 32 - 隐藏大部分信息(药水信息，书作者，烟花效果等等)
    - 63 - 隐藏所有的信息，除了名字和附加文字
- itemEnableRefresh(物品)
- itemFormatSet(物品 模板ID)
- itemInfoSet(物品 描述)
  - 多行以\n分割
- itemInfoAdd(物品 描述)
- itemBoreSet(物品 孔地址 最大镶嵌数量 被镶嵌物品属性地址 镶嵌物品属性地址)
  - 地址格式参考nbt中的地址格式
- itemSupportBoreAddressSet(物品 孔地址)
  - 这样设置后的物品可以镶嵌到有对应孔地址的物品
- addItemToBore(被镶嵌物品 镶嵌物品)
- itemAddSpell(物品 触发方式 Spell名 long(冷却时间))
  - 仅限主手物品
  - 触发方式
    - left
    - right
    - shiftLeft
    - shiftRight
- removeItemFromBore(物品 孔地址 序号)
  - 序号从0开始
- itemAddSpell(物品 魔咒名 冷却 生效位置 触发方式)
  - 触发方式
    - left
    - right
    - shiftLeft
    - shiftRight
- mergeNBT(物品1 物品2)
  - 将物品2的nbt合并到物品1中

### 返回值

- itemGivePlayer()
  - null
- itemSetPlayer()
  - null
- itemLoreAddLine()
  - item
- itemToString()
  - str
- stringToItem()
  - item
- itemNBTAdd()
  - null
- itemNBTGet()
  - object
- itemNBTRemove()
  - null
- itemGetName()
  - str
- itemSetName()
  - null
- itemTypeGet()
  - str
- itemTypeSet()
  - null
- itemLoreRemove()
  - null
- itemLoreHasLineNoColor()
  - boolean
- itemLoreHasLine()
  - boolean
- itemVarParse()
  - str
- newItem()
  - item
- refreshVar()
  - item
- itemModelGet()
  - int
- itemModelSet()
  - null
- itemEnchantmentGet()
  - int
- itemEnchantmentSet()
  - null
- itemDamageSet()
  - null
- itemDamageGet()
  - int
- itemAmountSet()
  - null
- itemAmountGet()
  - int
- itemAttributeSet()
  - null
- itemAttributeGet()
  - double
- itemFlag()
  - null
- itemEnableRefresh()
  - null
- itemFormatSet()
  - null
- itemInfoSet
  - item
- itemInfoAdd()
  - item
- itemBoreSet()
  - item
- itemSupportBoreAddressSet()
  - item
- addItemToBore()
  - item
- removeItemFromBore()
  - item
  - 镶嵌成功则语境获得变量removeItem
- itemAddSpell()
  - item
- mergeNBT()
  - item

## Yaml

### 实现

- yamlManager()
  - 创建Yaml管理器
- newYaml()
  - 使用管理器新建yaml文件
- yamlGet()
  - 从yaml中获取数据
- yamlSet()
  - 将值存入yaml
- yamlSave()
  - 将管理器中某个yaml文件保存到硬盘
- yamlSaveAll()
  - 保存管理器中全部的yaml文件
- hasyamlkey()
  - 检查是否有指定的键
- hasyaml()
  - 检查是否有指定名称的yaml文件

### 参数格式

- yamlManager(路径 文件夹名)
- newYaml(Yaml管理器 文件id)
- yamlGet(Yaml管理器 文件id 键)
- yamlSet(Yaml管理器 文件id 键 值)
- yamlSave(Yaml管理器 文件id)
- yamlSaveAll(Yaml管理器)
- hasyamlkey(Yaml管理器 文件id 键)
- hasyaml(Yaml管理器 文件id)

### 返回值

- yamlManager()
  - Yaml管理器
- newYaml()
  - null
- yamlGet()
  - object
- yamlSet()
  - null
- yamlSave()
  - null
- yamlSaveAll()
  - null
- hasyamlkey()
  - boolean
- hasyaml()
  - boolean

## 随机

### 实现

- randomDouble()
  - 区间内生成随机小数
- weightRandom()
  - 指定权重随机选取字符串
- randomUUID()
  - 生成随机uuid

### 参数格式

- randomDouble(起始值 终止值 保留位数)
- weightRandom(字符串1 字符串2 字符串3...)
  - 字符串格式
    - 内容|权重
      - ABC|10
- randomUUID()

### 返回值

- randomDouble()
  - double
- weightRandom
  - str
- randomUUID()
  - str

## 生物

### 实现

- newEntity()
  - 创建一个新的实体

### 参数格式

- newEntity(实体类型 坐标)

### 返回值

- newEntity()
  - entity

## BOSS血条

### 实现

- showBossBar()
  - 显示boss血条
- hideBossBar()
  - 隐藏boss血条
- bossbarVisible()
  - 设置boss血条可见性
- bossbarGet()
  - 获得boss血条
- addFlag()
  - 添加boss血条flag

### 参数格式

- showBossBar(玩家 bossbar)
- hideBossBar(玩家 bossbar)
- bossbarVisible(true/false)
- bossbarGet(key)
- addFlag(flag)
  - 可用flag
    - https://hub.spigotmc.org/javadocs/spigot/org/bukkit/boss/BarFlag.html

### 返回值

- showBossBar()
  - null
- hideBossBar()
  - null
- bossbarVisible()
  - null
- bossbarGet()
  - bossbar
- addFlag()
  - null

