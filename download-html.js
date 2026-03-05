import fs from 'fs';

async function downloadHtml(url, dest) {
    try {
        const res = await fetch(url, {
            headers: {
                'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36'
            }
        });

        if (!res.ok) {
            console.error(`Failed to fetch ${url}: ${res.status} ${res.statusText}`);
            return false;
        }

        const text = await res.text();
        fs.writeFileSync(dest, text);
        console.log(`Successfully downloaded to ${dest}`);
        return true;
    } catch (err) {
        console.error(`Error downloading ${url}:`, err);
        return false;
    }
}

async function main() {
    console.log("Downloading Login HTML...");
    await downloadHtml(
        'https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJ7Eh1hcHBfY29tcGFuaW9uX2dlbmVyYXRlZF9maWxlcxpaCiVodG1sX2I4ZTQ2YmFkMTVhODQ0YjA5OTcyOGRmZGI2YThmMDgyEgsSBxCrgNS7ng8YAZIBIwoKcHJvamVjdF9pZBIVQhM1ODU3NDQ2ODg4ODIxNTE5NDI3&filename=&opi=96797242',
        'c:/Users/Hack_Josh/Desktop/Antigravity/vizo-app/login.html'
    );

    console.log("Waiting 2 seconds to avoid rate limits...");
    await new Promise(r => setTimeout(r, 2000));

    console.log("Downloading Signup HTML...");
    await downloadHtml(
        'https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJ7Eh1hcHBfY29tcGFuaW9uX2dlbmVyYXRlZF9maWxlcxpaCiVodG1sX2YwNjVhZWVhY2I5NjRlOWZiOWFhYjE1MjA5ZGIwMzg3EgsSBxCrgNS7ng8YAZIBIwoKcHJvamVjdF9pZBIVQhM1ODU3NDQ2ODg4ODIxNTE5NDI3&filename=&opi=96797242',
        'c:/Users/Hack_Josh/Desktop/Antigravity/vizo-app/signup.html'
    );
}

main();
