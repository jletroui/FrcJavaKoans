try {
    del .\.vscode\launch.json 2>$null
}
finally {
    Compress-Archive -LiteralPath './.vscode','./images','./src','./README.md' -DestinationPath './FrcJavaKoans.zip' -Force
}
