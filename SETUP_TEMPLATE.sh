#!/bin/bash



RED='\033[1;31m'
# 读取 GitHub 用户名和项目名称
NAME_AND_PROJECT_UNPARSED=$(git ls-remote --get-url)
NEW_USERNAME=$(echo "$NAME_AND_PROJECT_UNPARSED" | cut -d':' -f 2 | cut -d'/' -f 1)
PROJECT_NAME=$(echo "$NAME_AND_PROJECT_UNPARSED" | cut -d'/' -f 2 | cut -d'.' -f 1)
NEW_EMAIL=$(git config user.email)
TEMP_TEST_OUTPUT=".ignore.test_output.txt"
PROJECT_TYPE="repository" # 如果未指定则默认值
will_omit_verification=false
will_omit_commit=false
will_omit_test=false
SCRIPT_VERSION="1.11.8"

FILE_FUNCTION_HELPERS=bin/FUNCTION_HELPERS.sh

if [ ! -f "$FILE_FUNCTION_HELPERS" ]; then # 检查函数助手文件是否不存在
  echo -e "${RED}X 无法找到 ${FILE_FUNCTION_HELPERS}"
  exit 1 # 如果找不到函数助手文件，则退出
else
  # shellcheck source=bin/FUNCTION_HELPERS.sh disable=SC1091
  source $FILE_FUNCTION_HELPERS || exit 1 # 如果找不到文件则退出，并获取一些全局函数和变量
fi

# 解析参数
for i in "$@"; do
  case $i in
  -u=* | --user=* | --username=* | --name=*)
    NEW_USERNAME="${i#*=}"
    shift # 过参数=值
    ;;
  -p=* | --project=* | --project-name=* | --project_name=* | --projectName=*)
    PROJECT_NAME="${i#*=}"
    shift # 过参数=值
    ;;
  -e=* | --email=* | --mail=*)
    NEW_EMAIL="${i#*=}"
    shift # 过参数=值
    ;;
  -t=* | --type=* | --project_type=* | --projectType=*)
    PROJECT_TYPE="${i#*=}"
    shift # 过参数=值
    ;;
  -h | --help | --info | --information)
    displayHelpTexts
    exit 0
    shift # 过参数=值
    ;;
  -v | --version)
    echo -e "${GREEN}$SCRIPT_VERSION${NC}"
    exit 0
    shift # 过参数=值
    ;;
  -o | --omit | --omit-commit-and-confirmation)
    echo -e "${BBLUE}X 已弃用:${NC} 参数 '--omit-commit-and-confirmation'，'-o' 和 '--omit' 已${RED}弃用${NC}。请改用 '--omit-verification' 和/或 ' --omit-commit'。"
    will_omit_verification=true
    will_omit_commit=true
    choice="y"
    shift # 过没有值的参数
    ;;
  --omit-verification)
    will_omit_verification=true
    choice="y"
    shift # 过没有值的参数
    ;;
  --omit-commit)
    will_omit_commit=true
    shift # 过没有值的参数
    ;;
  --omit-test-check | --omit-tests-check | --omit-tests)
    will_omit_test=true
    shift # 过没有值的参数
    ;;
  *) # 未知选项
    echo -e "${RED}X 未知选项:${NC} '${i}'，键入标志 '${BBLUE}--help${NC}' 查看所有选项和标志。"
    ;;
  esac
done

echo -e "感谢使用 ${GREEN}@SirYuxuan/project-template${NC}"
echo -e "在继续执行此脚本之前，请仔细阅读所有文档: ${UPURPLE}https://github.com/SirYuxuan/project-template${NC}\n"

bash tests/TESTS_RUNNER.sh >/dev/null 2>&1 # 执行测试

if [ "$?" -eq 1 ] && [ $will_omit_test = false ]; then # 如果运行测试时发现任何错误
  rm "$TEMP_TEST_OUTPUT" 2>/dev/null || :
  displayTestErrorTexts
  exit 1
fi

rm "$TEMP_TEST_OUTPUT" 2>/dev/null || :

if [ "$PROJECT_TYPE" = "repository" ]; then # 如果未手动指定项目类型
  read -p "输入 $(echo -e "$BBLUE""你的项目是什么""$NC") (程序/扩展/API/网页/CLI工具/后端/前端/爬虫/自动化工具等): " PROJECT_TYPE
fi

if [ $will_omit_verification = false ]; then # 如果未手动指定忽略标志
  read -p "数据是否正确: 用户名 \"$(echo -e "$GREEN""$NEW_USERNAME""$NC")\"，邮箱: \"$(echo -e "$GREEN""$NEW_EMAIL""$NC")\"，项目名称: \"$(echo -e "$GREEN""$PROJECT_NAME""$NC")\"，类型: \"$(echo -e "$GREEN""$PROJECT_TYPE""$NC")\" (y/n)? " choice
fi

# 确认数据正确
case "$choice" in
y | Y)
  center "为您设置一切 ;)"

  # 替换用户名和电子邮件
  find .github/ -type f -name "*" -print0 | xargs -0 sed -i "s/SirYuxuan/${NEW_USERNAME}/g"
  find .github/ -type f -name "*" -print0 | xargs -0 sed -i "s/1718018032@qq.com/${NEW_EMAIL}/g"
  find .github/ -type f -name "*" -print0 | xargs -0 sed -i "s/project-template/${PROJECT_NAME}/g"
  find .gitignore -type f -name "*" -print0 | xargs -0 sed -i "s/SirYuxuan\/project-template/${NEW_USERNAME}\/${PROJECT_NAME}/g"

  rm LICENSE 2>/dev/null || :                                 # 删除许可证
  rm -r bin/ 2>/dev/null || :                                 # 删除 bin 文件夹
  rm -r tests/ 2>/dev/null || :                               # 删除 tests 文件夹
  rm -r .github/workflows/ 2>/dev/null || :                   # 删除工作流文件夹
  writeREADME                                                 # 写入新的 README.md
  writeCHANGELOG                                              # 写入 CHANGELOG.md 的基本结构
  echo -e "# 添加你自己的资金链接" >.github/FUNDING.yml # 删除作者的自定义资金链接

  if [ $will_omit_commit = false ]; then                                                  # 如果已指定忽略选项用于测试
    git add CHANGELOG.md README.md .gitignore .github SETUP_TEMPLATE.sh LICENSE bin tests # 提交新文件
    git -c color.status=always status | less -REX                                         # 以彩色显示 git 状态
    echo -e "为您提交更改 :)\n"
    git commit -m "📝 设置 '@SirYuxuan/project-template' 模板: 通过执行 SETUP_TEMPLATE.sh 脚本个性化文件。🚀"
    echo -e "\n记得审查每个文件并根据您的喜好进行自定义。\n您已准备好开始您全新的出色项目🚀🚀。" else
  fi

  # 自删除此脚本
  rm -- "$0" 2>/dev/null || :
  ;;
n | N)
  echo -e "\n如果您的用户名、项目名称或电子邮件不正确，可以手动更改它们。阅读脚本帮助中的如何操作: ${UPURPLE}bash SETUP_TEMPLATE.sh --help${NC}\n"
  ;;
*) echo -e "${RED}X 无效选项${NC}" ;;
esac

exit 0
