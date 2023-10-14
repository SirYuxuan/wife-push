
# 🔥 **SirYuxuan/wife-push**

<div align="center">
  <br>
  <a href="https://github.com/SirYuxuan/wife-push/issues">
    <img src="https://img.shields.io/github/issues/SirYuxuan/wife-push?color=0088ff&style=for-the-badge&logo=github" alt="SirYuxuan/问题"/>
  </a>
  <a href="https://github.com/SirYuxuan/wife-push/pulls">
    <img src="https://img.shields.io/github/issues-pr/SirYuxuan/wife-push?color=0088ff&style=for-the-badge&logo=github"  alt="SirYuxuan/拉取请求"/>
  </a>
 <a href="https://docs.yuxuan66.com/open-source/wife-push">
    <img src="https://img.shields.io/badge/YuxuanOpenSource-Docs-blue"  alt="SirYuxuan/docs"/>
  </a>
</div>

---

## 🤔 **关于项目**

- **为什么创建此项目？**
- 为了给老婆每天早上推送一些信息，比如天气、每日一句等等
- 起源于小红书看到了一个同行给老婆做的微信公众号推送此类信息，觉得挺有趣就去看了看,没想到公众号都注册认证过了发现无法自定义模板了，所以采用了QQ推送的方案

---

## ⚡ **安装**

```shell
git clone git@github.com:SirYuxuan/wife-push.git
mvn clean package
# 生成的jar包在target目录下
# 正常启动，首次运行需要正常启动,用于扫码登录QQ
java -jar wifePush.jar
# linux下后台启动
nohup java -jar wifePush.jar > wifePush.log 2>&1 &
# 不需要日志文件
nohup java -jar wifePush.jar > /dev/null 2>&1 &
```

---

## 🚀 **用法**

* 修改application.yml的配置
```yaml
bot:
  # 机器人QQ号
  qq: 1718018032
  # 老婆的QQ
  wifeQQ: 1718018032
push:
  # 老婆的生日
  birthday: 1990-01-01
  # 认识的日子
  acquaintance: 1990-01-01
  # 恋爱纪念日
  loveDay: 1990-01-01
  # 领证纪念日
  obtainingACertificate: 1990-01-01
  # 结婚纪念日
  weddingAnniversary: 1990-01-01
  # 星座
  star: 双鱼座

# 获取彩虹屁和天气的接口Key https://www.tianapi.com/
tianxing:
  apiKey: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
  # 城市编码，可以在 https://tianapi.com/apiview/239 获取
  cityCode: 101180101
```
系统会在启动推送早上/晚上的消息，早上定时为7.15，晚上为19.15，此时间在`com.yuxuan66.wife.cron.CronConst`中修改

---

## 🌲 **项目树**

```
.
├── images
│   └── demo.png
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── yuxuan66
│       │           └── wife
│       │               ├── cron
│       │               │   └── CronConst.java
│       │               ├── entity
│       │               │   └── WeatherInfo.java
│       │               ├── service
│       │               │   ├── DayCalcService.java
│       │               │   └── NormalService.java
│       │               ├── support
│       │               │   ├── config
│       │               │   │   └── BotConfig.java
│       │               │   └── BotCore.java
│       │               ├── task
│       │               │   └── NormalPush.java
│       │               ├── utils
│       │               │   ├── TianxingApi.java
│       │               │   └── YuxuanApi.java
│       │               └── WifePushApp.java
│       └── resources
│           ├── META-INF
│           │   └── additional-spring-configuration-metadata.json
│           ├── application.yml
│           └── banner.txt
├── CHANGELOG.md
├── LICENSE
├── pom.xml
└── readme.md

```

---

## 📝 **额外说明**

* 如需要什么额外功能请在`com.yuxuan66.wife.task`包下添加

---

## 📸 **效果截图**

<!-- ... [一些描述性图片] -->
![](./images/demo.png)

---

## 🍰 **支持者和捐赠者**


我们目前正在寻找新的捐赠者来帮助维护此项目！❤️

通过捐赠，您将帮助此项目的发展，并且*您将在此wife-push的README.md中显示*，以便每个人都可以看到您的善举并访问您的内容⭐。

<a target="_blank" href="https://afdian.net/a/siryuxuan/plan"> <!-- 如果您不在GitHub赞助计划中，修改此链接到您的主要捐赠网站 -->
  <img src="https://img.shields.io/badge/Sponsor-SirYuxuan/wife push-blue?logo=github-sponsors&style=for-the-badge&color=red">
</a>

<!-- 在此处链接到您的捐赠页面 -->

---

wife-push从*[SirYuxuan/project-template](https://github.com/SirYuxuan/project-template)* 📚生成的

---

## 🕵️ 额外建议

* 疼老婆会发达，爱老婆会发达

---

## 🎉 wife-push有帮助吗？帮助我们提高这些数字

[![GitHub followers](https://img.shields.io/github/followers/SirYuxuan.svg?style=social)](https://github.com/SirYuxuan)
[![GitHub stars](https://img.shields.io/github/stars/SirYuxuan/wife-push.svg?style=social)](https://github.com/SirYuxuan/wife-push/stargazers)
[![GitHub watchers](https://img.shields.io/github/watchers/SirYuxuan/wife-push.svg?style=social)](https://github.com/SirYuxuan/wife-push/watchers)
[![GitHub forks](https://img.shields.io/github/forks/SirYuxuan/wife-push.svg?style=social)](https://github.com/SirYuxuan/wife-push/network/members)
[![赞助](https://img.shields.io/static/v1?label=赞助&message=%E2%9D%A4&logo=github-sponsors&color=red&style=social)](https://afdian.net/a/siryuxuan/plan)

尽情享受！ 😃

---

## ⚖️📝 **许可和更改记录**

请查看 '**[LICENSE](LICENSE)**' 文件中的许可证。

请查看 '**[CHANGELOG.md](CHANGELOG.md)**' 文件中的更改。

---

_由**[@SirYuxuan](https://github.com/SirYuxuan)**充满了很多❤️❤️制作_
