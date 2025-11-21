# 📌 Eventify – Application de gestion d’événements sécurisée (Spring Boot + Spring Security)

Eventify est une application web permettant de gérer des événements avec une authentification sécurisée basée sur **Spring Security (Basic Auth)**.  
Le système est entièrement séparé en rôles : **USER**, **ORGANIZER**, **ADMIN**, chacun ayant accès à des fonctionnalités spécifiques.

Ce projet a été réalisé dans le cadre du module Spring Security et met l’accent sur une sécurité robuste, un code propre et une architecture claire.

---

## 🚀 Fonctionnalités principales

### 👤 Gestion des utilisateurs
- Inscription avec rôle par défaut : `ROLE_USER`
- Authentification Basic Auth
- Modification du rôle par l’administrateur
- Service utilisateur complet : création, lecture, mise à jour, suppression

### 🎟 Gestion des événements
- Création / modification / suppression par `ROLE_ORGANIZER`
- Liste publique des événements
- Inscription à un événement pour les utilisateurs
- Gestion des inscriptions

### 🔐 Sécurité avancée
- `CustomAuthenticationProvider`
- `UserDetailsService` personnalisé
- `BCryptPasswordEncoder`
- Architecture **100% stateless**
- Gestion fine des autorisations par endpoints
- Gestion centralisée des exceptions (401/403)

---

## 🛠 Architecture technique

- Spring Boot 3+
- Spring Security
- MySQL + JPA/Hibernate
- Lombok
- Exception Handling via `@RestControllerAdvice`
- Tests via profil Spring `test`

### 🔒 Règles de sécurité

| Endpoint | Accès |
|----------|-------|
| `/api/public/**` | Public |
| `/api/user/**` | `ROLE_USER` |
| `/api/organizer/**` | `ROLE_ORGANIZER` |
| `/api/admin/**` | `ROLE_ADMIN` |

---

## 📂 Modèle de données

### **User**
- id  
- name  
- email  
- password  
- role (`ROLE_USER`, `ROLE_ORGANIZER`, `ROLE_ADMIN`)

### **Event**
- id  
- title  
- description  
- location  
- dateTime  
- capacity  
- organizerId  

### **Registration**
- id  
- userId  
- eventId  
- registeredAt  
- status  

---

## 🧭 Endpoints de l’API

### 🌍 Public
| Méthode | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/public/users` | Inscription |
| GET | `/api/public/events` | Liste des événements publics |

---

### 👤 User
| Méthode | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/user/profile` | Profil |
| POST | `/api/user/events/{id}/register` | Inscription à un événement |
| GET | `/api/user/registrations` | Historique des inscriptions |

---

### 🧑‍💼 Organizer
| Méthode | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/organizer/events` | Créer un événement |
| PUT | `/api/organizer/events/{id}` | Modifier un événement |
| DELETE | `/api/organizer/events/{id}` | Supprimer |

---

### 🛡 Admin
| Méthode | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/admin/users` | Liste des utilisateurs |
| PUT | `/api/admin/users/{id}/role` | Modifier rôle utilisateur |
| DELETE | `/api/admin/events/{id}` | Supprimer un événement |

---

## 🛡 Gestion des erreurs & exceptions

Un `@RestControllerAdvice` centralise les erreurs.

### Format standard JSON :

```json
{
  "timestamp": "2024-11-21T12:00:00",
  "status": 403,
  "error": "Forbidden",
  "message": "Access denied",
  "path": "/api/admin/users"
}



