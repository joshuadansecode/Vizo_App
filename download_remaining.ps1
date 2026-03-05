$urls = @{
    "onboarding-performance.html" = "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJ7Eh1hcHBfY29tcGFuaW9uX2dlbmVyYXRlZF9maWxlcxpaCiVodG1sX2I1MzI1NjgwYjc3YjQyNDJiZTFhMGJlZTcxMGNiYjlkEgsSBxCrgNS7ng8YAZIBIwoKcHJvamVjdF9pZBIVQhM1ODU3NDQ2ODg4ODIxNTE5NDI3&filename=&opi=96797242"
    "contacts.html" = "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJ7Eh1hcHBfY29tcGFuaW9uX2dlbmVyYXRlZF9maWxlcxpaCiVodG1sXzhhNTU2ZTM4ZjZhMzQ5ZWZiMTEwNzIyZTI5YTAxNjMwEgsSBxCrgNS7ng8YAZIBIwoKcHJvamVjdF9pZBIVQhM1ODU3NDQ2ODg4ODIxNTE5NDI3&filename=&opi=96797242"
    "create-status.html" = "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJ7Eh1hcHBfY29tcGFuaW9uX2dlbmVyYXRlZF9maWxlcxpaCiVodG1sXzE2YzRkNmIwNjVhMjQ5NTU4ZDEwMmIwZTQwYjZmMzExEgsSBxCrgNS7ng8YAZIBIwoKcHJvamVjdF9pZBIVQhM1ODU3NDQ2ODg4ODIxNTE5NDI3&filename=&opi=96797242"
    "leaderboard.html" = "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJ7Eh1hcHBfY29tcGFuaW9uX2dlbmVyYXRlZF9maWxlcxpaCiVodG1sXzFjNzA3NjM3MWNkNjRiOGY4M2ZjZTVlNGM1YzAxNjY5EgsSBxCrgNS7ng8YAZIBIwoKcHJvamVjdF9pZBIVQhM1ODU3NDQ2ODg4ODIxNTE5NDI3&filename=&opi=96797242"
}

foreach ($key in $urls.Keys) {
    $url = $urls[$key]
    $dest = "temp_htmls\$key"
    Write-Host "Downloading $key..."
    try {
        Invoke-WebRequest -Uri $url -OutFile $dest -UseBasicParsing
        Start-Sleep -Seconds 5
    } catch {
        Write-Host "Failed to download $key : $_"
    }
}
Write-Host "Done!"
