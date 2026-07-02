# 🚀 Employee Payroll System — Daily Startup Guide

Har baar jab system restart ho ya naya session shuru karna ho, ye steps follow karo.

---

## ✅ Step 1: MySQL Chalu Karo

MySQL service running honi chahiye. Check karne ke liye:

**Windows Services se:**
1. `Win + R` dabao → type `services.msc` → Enter
2. "MySQL80" (ya jo bhi naam ho) dhundo
3. Agar "Running" nahi hai → Right-click → **Start**

**Ya agar XAMPP/WAMP use karte ho:**
- XAMPP Control Panel kholo → MySQL ke aage **Start** dabao

---

## ✅ Step 2: Backend (Spring Boot) Start Karo

### **IntelliJ Se:**
1. IntelliJ IDEA kholo
2. Project already khula hoga (ya File → Open → `Employee Payroll System` folder select karo)
3. `PayrollApplication.java` file dhundo:
   ```
   src/main/java/com/abhishek/payroll/PayrollApplication.java
   ```
4. Green ▶️ Run button dabao

### **Terminal Se (Alternative):**
```powershell
cd "C:\Users\HP\Downloads\Employee Payroll System"
mvn spring-boot:run
```

**Confirm karo ye line dikhe:**
```
Tomcat started on port 8080
Started PayrollApplication in X seconds
```

**Test karo:** Browser mein `http://localhost:8080/api/employees` kholo — JSON data dikhna chahiye.

---

## ✅ Step 3: Frontend (React) Start Karo

**Naya PowerShell/Terminal window kholo** (backend wala band mat karo):

```powershell
cd "C:\Users\HP\Downloads\Employee Payroll System\frontend"
npm run dev
```

**Ye dikhega:**
```
VITE ready in xxx ms
➜  Local:   http://localhost:5173/
```

**Browser mein kholo:** `http://localhost:5173`

---

## ✅ Step 4: Verify Everything Works

- [ ] Backend: `http://localhost:8080/api/employees` → JSON data dikhe
- [ ] Frontend: `http://localhost:5173` → Employee table dikhe
- [ ] Add/Edit/Delete employee try karo

---

## 🛑 Band Kaise Karo (Jab Kaam Khatam)

1. **Frontend Terminal** mein → `Ctrl + C` dabao
2. **IntelliJ** mein → Red ⏹️ Stop button dabao (backend ke liye)
3. Chaho to MySQL bhi band kar sakte ho (zaroori nahi)

---

## 🆘 Common Issues & Quick Fixes

| Problem | Fix |
|---------|-----|
| Backend "Cannot connect to database" | MySQL start karo (Step 1) |
| Frontend blank/white page | Hard refresh: `Ctrl + Shift + R` |
| `npm run dev` → "Missing script" error | Galat folder mein ho — `cd frontend` pehle karo |
| Port 8080 already in use | Purana process band karo: `taskkill /F /IM java.exe` |
| Port 5173 already in use | Purana process band karo: `taskkill /F /IM node.exe` |
| "Found 0 JPA repository" | Purana build cache — `mvn clean install` chalao |

---

## 📝 Quick Command Cheat Sheet

```powershell
# Backend start
cd "C:\Users\HP\Downloads\Employee Payroll System"
mvn spring-boot:run

# Frontend start (naya terminal mein)
cd "C:\Users\HP\Downloads\Employee Payroll System\frontend"
npm run dev

# Agar processes stuck ho jayein
taskkill /F /IM java.exe
taskkill /F /IM node.exe

# Fresh build (agar backend mein changes kiye ho)
mvn clean install

# Git status check karo
git status

# Changes save karo
git add .
git commit -m "your message here"
git push origin main
```

---

## 💡 Pro Tip: Do Terminal Windows Rakho

**Terminal 1 (Backend):**
```powershell
cd "C:\Users\HP\Downloads\Employee Payroll System"
mvn spring-boot:run
```

**Terminal 2 (Frontend):**
```powershell
cd "C:\Users\HP\Downloads\Employee Payroll System\frontend"
npm run dev
```

Dono ek saath chalte rehne chahiye jab tak kaam kar rahe ho.

---

**Bas itna hi! Ab har din 2 minute mein project start ho jayega.** 🚀
