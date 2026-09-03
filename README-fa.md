# پارک‌بان — پروژه Android و Build خودکار APK

این پروژه فقط برای **Android** است.

## ساخت خودکار APK

فایل `.github/workflows/build-apk.yml` با هر Push روی شاخه `main` یا `master` و همچنین با اجرای دستی Workflow، APK را می‌سازد.

### خروجی اصلی برای نصب روی گوشی

`ParkBan.apk` از نوع **Debug APK** است و برای نصب و تست روی گوشی مناسب است.

### خروجی Release

`app-release-unsigned.apk` نیز تولید می‌شود، اما برای انتشار رسمی در Google Play یا نصب به‌عنوان نسخه نهایی بهتر است با کلید امضای خودتان Sign شود.

## فعال‌سازی Push Notification با Firebase

ساخت APK بدون Firebase هم انجام می‌شود. برای فعال‌شدن واقعی FCM:

1. در Firebase یک Android App با package name زیر بسازید:
   `ir.parkban.app`
2. فایل `google-services.json` را تهیه کنید.
3. محتوای Base64 آن را در GitHub Actions Secret با نام زیر قرار دهید:
   `GOOGLE_SERVICES_JSON_BASE64`
4. Workflow را دوباره اجرا کنید.

Workflow در صورت وجود این Secret، فایل Firebase را قبل از Build می‌سازد و Google Services plugin را خودکار فعال می‌کند.

## نکته امنیتی

`google-services.json` و کلید امضای APK را داخل مخزن عمومی GitHub قرار ندهید.
