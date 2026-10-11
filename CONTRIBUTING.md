# Contributing

Start with one example or one documentation problem. These are historical learning projects; a focused, tested improvement is easier to review than a repository-wide framework upgrade.

1. Fork the repository and create a branch for one change.
2. Identify the exact example folder and inspect its POM or Eclipse project settings.
3. Reproduce the current behavior using a compatible toolchain and synthetic data.
4. Make the smallest useful fix. Keep existing attribution and third-party license notices.
5. In the pull request, describe the problem, changed behavior, commands you ran, and any checks you could not run.

Useful contributions include a confirmed setup fix, a clearer source link, an accurate expected-output example, a comparison of two existing implementations, and an isolated modernization with compatibility notes.

Never include live database credentials, cloud resource addresses, private filesystem paths, tokens, real user records, or unsanitized logs/screenshots. Configure local secrets outside Git. If a secret was already committed, revoke or rotate it first; removing it in a later commit does not erase history. Use GitHub's private security reporting if enabled, or request a private contact channel without putting the secret in an issue.

There is no blanket promise that every legacy module builds. Report the exact module and toolchain instead of claiming all examples are tested. Do not add fake badges, benchmark numbers, stars, traffic figures, or mass promotional changes.

Your original additions are contributed under the root MIT license. Do not copy material with incompatible or unknown licensing; cite sources and preserve their terms.
