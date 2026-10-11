# Local database configuration

Use only a disposable local MySQL database with synthetic records. Configuration reads environment variables; no public database endpoint or password belongs in the source tree.

| Variable | Purpose |
| --- | --- |
| `SPRING_DATASOURCE_URL` | Your local JDBC URL, for example `jdbc:mysql://localhost:3306/native_demo` |
| `SPRING_DATASOURCE_USERNAME` | A database user limited to your demo schema |
| `SPRING_DATASOURCE_PASSWORD` | That user's password, supplied outside Git |

On Bash, set the URL and username with `export`, then enter the password without putting it in shell history:

```sh
read -rsp 'Local demo database password: ' SPRING_DATASOURCE_PASSWORD
printf '\n'
export SPRING_DATASOURCE_PASSWORD
```

On PowerShell, set the URL and username through `$env:SPRING_DATASOURCE_URL` and `$env:SPRING_DATASOURCE_USERNAME`. Prompt for the password with `Read-Host -AsSecureString`; use a credential object to obtain it for the process environment:

```powershell
$demoSecret = Read-Host 'Local demo database password' -AsSecureString
$demoCredential = [System.Management.Automation.PSCredential]::new('unused', $demoSecret)
$env:SPRING_DATASOURCE_PASSWORD = $demoCredential.GetNetworkCredential().Password
```

Environment variables are visible to sufficiently privileged local processes; this is a local development setup, not a production secret manager. Spring Boot does not automatically load a `.env` file. Keep real secrets and records outside Git, and clear the process environment when finished.

Earlier commits contained database connection credentials. Rotate or revoke any still-live credential and review database access. The current environment-based configuration does not remove those values from Git history. Never test against a previously committed cloud endpoint.
