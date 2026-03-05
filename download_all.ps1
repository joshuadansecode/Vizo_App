$urls = @{
    "onboarding-performance.html" = "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJ7Eh1hcHBfY29tcGFuaW9uX2dlbmVyYXRlZF9maWxlcxpaCiVodG1sX2I1MzI1NjgwYjc3YjQyNDJiZTFhMGJlZTcxMGNiYjlkEgsSBxCrgNS7ng8YAZIBIwoKcHJvamVjdF9pZBIVQhM1ODU3NDQ2ODg4ODIxNTE5NDI3&filename=&opi=96797242"
    "onboarding-earn.html" = "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJ7Eh1hcHBfY29tcGFuaW9uX2dlbmVyYXRlZF9maWxlcxpaCiVodG1sXzY1ZDBiMTU2NjMxZDRlMTZiN2FjNGY5NWU1NTJkZGExEgsSBxCrgNS7ng8YAZIBIwoKcHJvamVjdF9pZBIVQhM1ODU3NDQ2ODg4ODIxNTE5NDI3&filename=&opi=96797242"
    "onboarding-network.html" = "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJ7Eh1hcHBfY29tcGFuaW9uX2dlbmVyYXRlZF9maWxlcxpaCiVodG1sXzhkY2Q0ZDdiMTVmZTQ1ZDhiODk3YWNhMmE3OTAyOGVmEgsSBxCrgNS7ng8YAZIBIwoKcHJvamVjdF9pZBIVQhM1ODU3NDQ2ODg4ODIxNTE5NDI3&filename=&opi=96797242"
    "onboarding-viral.html" = "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJ7Eh1hcHBfY29tcGFuaW9uX2dlbmVyYXRlZF9maWxlcxpaCiVodG1sX2NlZmMyZjdiOWY3NDRiZGRiYzE3MzQ0Mjg0MmVhYzk2EgsSBxCrgNS7ng8YAZIBIwoKcHJvamVjdF9pZBIVQhM1ODU3NDQ2ODg4ODIxNTE5NDI3&filename=&opi=96797242"
    "contacts.html" = "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJ7Eh1hcHBfY29tcGFuaW9uX2dlbmVyYXRlZF9maWxlcxpaCiVodG1sXzhhNTU2ZTM4ZjZhMzQ5ZWZiMTEwNzIyZTI5YTAxNjMwEgsSBxCrgNS7ng8YAZIBIwoKcHJvamVjdF9pZBIVQhM1ODU3NDQ2ODg4ODIxNTE5NDI3&filename=&opi=96797242"
    "boosts.html" = "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJ7Eh1hcHBfY29tcGFuaW9uX2dlbmVyYXRlZF9maWxlcxpaCiVodG1sXzFmZTg3MGUyN2U0ODQ2YjFiMWJkMTA2NTE4MTZjYjYxEgsSBxCrgNS7ng8YAZIBIwoKcHJvamVjdF9pZBIVQhM1ODU3NDQ2ODg4ODIxNTE5NDI3&filename=&opi=96797242"
    "create-status.html" = "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJ7Eh1hcHBfY29tcGFuaW9uX2dlbmVyYXRlZF9maWxlcxpaCiVodG1sXzE2YzRkNmIwNjVhMjQ5NTU4ZDEwMmIwZTQwYjZmMzExEgsSBxCrgNS7ng8YAZIBIwoKcHJvamVjdF9pZBIVQhM1ODU3NDQ2ODg4ODIxNTE5NDI3&filename=&opi=96797242"
    "profile.html" = "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJ7Eh1hcHBfY29tcGFuaW9uX2dlbmVyYXRlZF9maWxlcxpaCiVodG1sXzhlZWI4MjMyNWNkNjRhZjg4MWVjMDY4OTVlMGY1ZjEzEgsSBxCrgNS7ng8YAZIBIwoKcHJvamVjdF9pZBIVQhM1ODU3NDQ2ODg4ODIxNTE5NDI3&filename=&opi=96797242"
    "leaderboard.html" = "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJ7Eh1hcHBfY29tcGFuaW9uX2dlbmVyYXRlZF9maWxlcxpaCiVodG1sXzFjNzA3NjM3MWNkNjRiOGY4M2ZjZTVlNGM1YzAxNjY5EgsSBxCrgNS7ng8YAZIBIwoKcHJvamVjdF9pZBIVQhM1ODU3NDQ2ODg4ODIxNTE5NDI3&filename=&opi=96797242"
}

New-Item -ItemType Directory -Force -Path temp_htmls | Out-Null

foreach ($key in $urls.Keys) {
    $url = $urls[$key]
    $dest = "temp_htmls\$key"
    Write-Host "Downloading $key..."
    Invoke-WebRequest -Uri $url -OutFile $dest
}
Write-Host "Done!"
