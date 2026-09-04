# ParkBan Android Auto Build

این بسته شامل Workflow واقعی GitHub Actions است.

## نصب در GitHub
1. در Repository به `.github/workflows/` بروید.
2. فایل `android.yml` را در آنجا قرار دهید.
3. به Actions بروید و `Build ParkBan APK` را اجرا کنید.
4. بعد از سبز شدن Build، از بخش Artifacts فایل `ParkBan-APK` را دانلود کنید.
5. داخل ZIP، فایل `ParkBan.apk` قرار دارد.

این Workflow خودش پروژه Android را هنگام Build ایجاد می‌کند؛ بنابراین Repository لازم نیست فایل‌های Gradle را از قبل داشته باشد.
