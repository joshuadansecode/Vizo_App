$urls = @{
    "contacts.html"      = "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJ7Eh1hcHBfY29tcGFuaW9uX2dlbmVyYXRlZF9maWxlcxpaCiVodG1sXzhhNTU2ZTM4ZjZhMzQ5ZWZiMTEwNzIyZTI5YTAxNjMwEgsSBxCrgNS7ng8YAZIBIwoKcHJvamVjdF9pZBIVQhM1ODU3NDQ2ODg4ODIxNTE5NDI3&filename=&opi=96797242"
    "create-status.html" = "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJ7Eh1hcHBfY29tcGFuaW9uX2dlbmVyYXRlZF9maWxlcxpaCiVodG1sXzE2YzRkNmIwNjVhMjQ5NTU4ZDEwMmIwZTQwYjZmMzExEgsSBxCrgNS7ng8YAZIBIwoKcHJvamVjdF9pZBIVQhM1ODU3NDQ2ODg4ODIxNTE5NDI3&filename=&opi=96797242"
    "leaderboard.html"   = "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJ7Eh1hcHBfY29tcGFuaW9uX2dlbmVyYXRlZF9maWxlcxpaCiVodG1sXzFjNzA3NjM3MWNkNjRiOGY4M2ZjZTVlNGM1YzAxNjY5EgsSBxCrgNS7ng8YAZIBIwoKcHJvamVjdF9pZBIVQhM1ODU3NDQ2ODg4ODIxNTE5NDI3&filename=&opi=96797242"
}

foreach ($key in $urls.Keys) {
    Write-Host "Downloading $key..."
    Invoke-WebRequest -Uri $urls[$key] -OutFile "temp_htmls\$key" -UseBasicParsing
    Start-Sleep -Seconds 15
}
Write-Host "Done!"
