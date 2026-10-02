# YouTube VPN

Android Studio / GitHub Actions starter project.

## What it does

- Opens https://www.youtube.com/ in an Android WebView.
- Requests Android VPN permission on first launch.
- Includes the Android VpnService shell.
- GitHub Actions builds a debug APK automatically.

## Important limitation

This repository intentionally does NOT pretend that a VpnService shell is a working VPN.

A real tunnel needs:

1. A VPN/proxy engine (for example sing-box/libbox).
2. A valid upstream server/profile (VLESS, WireGuard, etc.).
3. The engine must be connected to that upstream server.

Without an upstream server, no APK can magically route blocked YouTube traffic through another network.

## About using the separate sing-box Android app

The official sing-box Android client supports TUN through Android VpnService and per-app proxying. However, the public Android client does not currently expose a documented public Intent API for another app to start/stop its VPN. A 2026 feature request specifically asks for such Intent/Tasker controls.

Therefore this launcher cannot reliably say "open YouTube -> silently start the separately installed sing-box app".

## Practical no-server option

Install a VPN provider that supplies its own Android VPN and enable its Always-on VPN mode. Then this YouTube APK can simply open YouTube; Android keeps the provider VPN active.

For a self-contained APK, embed libbox and provide a real server profile.

## Build

Open this folder in Android Studio and sync Gradle.

Or push to GitHub. The workflow in `.github/workflows/build-apk.yml` builds the APK.

License: this starter code is original. If you add sing-box/libbox or copy code from the sing-box Android project, comply with the applicable GPL-3.0 license and attribution requirements.
