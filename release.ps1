try {
    del .\.vscode\launch.json 2>$null
}
finally {
    Compress-Archive -LiteralPath './.vscode','./images','./src','./README.md','./JavaCheatSheet.pdf','./AideMémoireJava.pdf' -DestinationPath './FrcJavaKoans.zip' -Force
}
