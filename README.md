# translatorAPI

![GitHub License](https://img.shields.io/github/license/Jaime-alv/cli-translator)
![GitHub Tag](https://img.shields.io/github/v/tag/Jaime-alv/cli-translator?color=009EDB)
![Static Badge](https://img.shields.io/badge/Coverage-%3E75%25-green?logo=apachemaven&logoColor=C71A36)


![Workflow](https://github.com/Jaime-alv/cli-translator/actions/workflows/maven.yml/badge.svg?label=Coverage)


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
java -jar translator-api.jar \ 
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
output="$(java -jar $HOME/.scripts/translator-api.jar -mode translate -text "${input}" -api-key $DEEPL -target $target_language -from $from_language)"
echo -e "${output}"
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

#### Add as shorcut for git

Edit .gitconfig file, located under $HOME dir.

```sh
[alias]
    tr = "!f () { msg=$(java -jar $HOME/.scripts/translator-api.jar -mode translate -text \"$1\" -api-key <API-KEY> -target GB -from ES) && git commit -am \"$msg\"; }; f"
```

Adding the API-KEY in a common file could make things easier if you need to track the api-key across several scripts.

Create a `.keys` file under $HOME:

```
DEEPL="<your-api-key>"
```

Now it can be sourced from `.gitconfig` and `.bashrc`:

```sh
[alias]
    tr = "!f () { . $HOME/.keys && msg=$(java -jar $HOME/.scripts/translator-api.jar -mode translate -text \"$1\" -api-key $DEEPL -target GB -from ES) && git commit -am \"$msg\"; }; f"
```
