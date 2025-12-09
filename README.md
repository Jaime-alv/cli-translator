# translatorAPI

![GitHub License](https://img.shields.io/github/license/Jaime-alv/chat-translator)
![GitHub Tag](https://img.shields.io/github/v/tag/Jaime-alv/chat-translator?color=009EDB)
![Static Badge](https://img.shields.io/badge/scoverage-%3E90%25-green?logo=apachemaven&logoColor=C71A36)


![Workflow](https://github.com/Jaime-alv/chat-translator/actions/workflows/maven.yml/badge.svg?label=Scoverage)


Java App to connect to the public endpoint of DeepL and translate texts conveniently from the command line terminal.

## URLs

- [DeepL API Docs](https://deepl-c950b784.mintlify.app/docs/getting-started/intro)
- [API Endpoints](https://deepl-c950b784.mintlify.app/api-reference/translate)


## Building the package

### Pre-requisites

Optional: install java & [maven](https://maven.apache.org/).

Originally developed with [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).

Get a free API key from DeepL. [Start here!](https://developers.deepl.com/docs/getting-started/intro)

```sh
mvn clean package
```

```sh
export API_KEY = "<API-KEY>"
java -jar target/chat-translator-jar-with-dependencies.jar \ 
    -mode translate \ 
    -text "Construir una API de traducción" \ 
    -api-key $API_KEY
```

### Add as shortcut to the shell

Edit `.bashrc` (or similar) shell script and follow the next example:

```sh
DEEPL="<API-KEY>"

# Launches Java app to connect to the DeepL API
translator() {
input=$1
target_language=$2
from_language=$3
output="$(java -jar $HOME/scripts/chat-translator.jar -mode translate -text "${input}" -api-key $DEEPL -target $target_language -from $from_language)"
echo -e ">>>\n${output}\n>>>"
echo $output | clip.exe
}

# Next functions works as shortcuts.
# tr-es "Translate this to Spanish\!"

tr-gb(){
input="${*}"
translator "${input}" "GB" "ES"
}

tr-us(){
input="${*}"
translator "${input}" "US" "ES"
}

tr-es(){
input="${*}"
translator "${input}" "ES" "EN"
}
```

Piping the output to a clipboard is rather convenient, just paste the output directly where it's need it.
