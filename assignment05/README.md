# Assignment 05 - Online Voting System
## Complete JSP Voting System

### Overview
This is the final and complete implementation of the online voting system using JSP technology. It includes all features from previous assignments plus new functionality for a complete voting experience.

### New Features Added
1. **Sign Out Functionality**
   - Proper session management with logout
   - Session invalidation and cleanup
   - User-friendly logout confirmation

2. **Add Candidate (JSP Implementation)**
   - JSP-based candidate addition for admins
   - Form processing within JSP
   - Success/error message handling

3. **Edit Candidate Display (JSP)**
   - JSP implementation of candidate editing
   - Display-only form controls as required
   - Admin-only access control

4. **Vote Control System**
   - Users can only vote once
   - Vote tracking in database
   - Prevention of duplicate voting
   - Real-time vote counting

5. **Complete Dashboard Interface**
   - User-friendly dashboard after login
   - Role-based navigation
   - Quick access to all features

6. **Enhanced User Experience**
   - Real-time voting results with percentages
   - Visual vote distribution bars
   - Responsive design for all devices

### Complete Feature Set
- ✅ User Registration System
- ✅ JSP-Based Authentication
- ✅ Session Management
- ✅ Role-Based Access Control
- ✅ Candidate Management (Admin)
- ✅ Voting System with Duplicate Prevention
- ✅ Real-Time Results Display
- ✅ Sign Out Functionality
- ✅ Complete Dashboard Interface

### Files Structure
```
src/main/java/assign05/
├── entities/
│   ├── User.java          # User entity class
│   └── Candidate.java     # Candidate entity class
├── daos/
│   ├── UserDao.java       # User database operations
│   ├── CandidateDao.java  # Candidate database operations
│   └── VoteDao.java       # NEW: Vote tracking operations
├── beans/
│   └── LoginBean.java     # Authentication business logic
├── servlets/
│   ├── RegistrationServlet.java  # Handles user registration
│   ├── ResultServlet.java        # Displays election results
│   ├── AddCandidateServlet.java  # Adds new candidates
│   └── EditServlet.java          # Displays candidate details
└── utils/
    └── DbUtil.java        # Database utility class

src/main/webapp/
├── index.html             # Static landing page
├── index.jsp              # JSP login form
├── login.jsp              # Authentication processing
├── logout.jsp             # Session termination
├── dashboard.jsp          # NEW: User dashboard
├── vote.jsp               # NEW: Voting interface
├── castvote.jsp           # NEW: Vote processing
├── results.jsp            # NEW: Enhanced results display
├── addcandidate.jsp       # NEW: JSP candidate addition
├── editcandidate.jsp      # NEW: JSP candidate editing
├── managecandidates.jsp   # NEW: Candidate management
└── register.html          # User registration form
```

### New Components Details

#### VoteDao
- **Package**: `assign05.daos`
- **Methods**:
  - `hasUserVoted(int userId)`: Check if user has already voted
  - `castVote(int userId, int candidateId)`: Record vote with transaction
  - `getTotalVotes()`: Get total number of votes cast

#### Key JSP Pages

1. **dashboard.jsp**
   - Personalized welcome message
   - Role-based navigation cards
   - Quick access to all features
   - User information display

2. **vote.jsp**
   - Display all candidates with current vote counts
   - Radio button selection for voting
   - Prevents voting if user has already voted
   - Visual candidate cards with party information

3. **castvote.jsp**
   - Processes vote submission
   - Updates database with transaction safety
   - Prevents duplicate voting
   - Success/error message display

4. **results.jsp**
   - Real-time voting results
   - Percentage calculations
   - Visual vote distribution bars
   - Ranking system with colors

5. **addcandidate.jsp**
   - Admin-only candidate addition
   - Form processing within JSP
   - Success confirmation and error handling

6. **editcandidate.jsp**
   - Display candidate details in form controls
   - Read-only implementation as required
   - Admin access control

### Database Schema
```sql
-- Users table for authentication
users (id, first_name, last_name, email, password, dob, mobile, status, role)

-- Candidates table for election
candidates (id, name, party, votes)

-- Votes table for tracking (prevents duplicate voting)
votes (id, user_id, candidate_id, voted_at)
```

### Setup Instructions
1. Import the project into your IDE (Eclipse/IntelliJ)
2. Set up MySQL database using the provided `database_schema.sql`
3. Update database connection details in `DbUtil.java`
4. Deploy to a servlet container (Tomcat 9.0+)
5. Access the application at `http://localhost:8080/assign05/`

### User Workflow
1. **Registration**: New users register via `register.html`
2. **Login**: Users login via `index.jsp`
3. **Dashboard**: Redirected to personalized dashboard
4. **Voting**: Cast vote (only once per user)
5. **Results**: View real-time election results
6. **Admin**: Manage candidates (admin users only)
7. **Logout**: Sign out with session cleanup

### Demo Credentials
- **Admin**: admin@voting.com / admin123
- **Features**: Can add/edit candidates, view all results
- **Regular User**: Register new account or use existing

### Assignment 05 Requirements Fulfilled
✅ **Sign Out**: Complete session management with logout  
✅ **Add Candidate**: JSP-based candidate addition for admins  
✅ **Edit Candidate**: Display candidate details in form controls  
✅ **Vote Control**: Users can only vote once, duplicate prevention  
✅ **Complete Integration**: All features working together seamlessly  

### Technologies Used
- JSP (JavaServer Pages)
- JavaBeans
- Java Servlets
- MySQL Database with Transactions
- HTML5/CSS3
- JavaScript (for UI enhancements)
- JDBC for database connectivity
- Session management
- Responsive web design

### Security Features
- Password-based authentication
- Session-based access control
- Role-based feature access
- SQL injection prevention
- Duplicate vote prevention

### Performance Features
- Database connection pooling ready
- Efficient query design
- Minimal session data storage
- Responsive UI design

### Author
Nilesh Ghule <nilesh@sunbeaminfo.com>  
Sunbeam Institute of Information Technology, Pune & Karad

---
**Note**: This is the complete implementation of the online voting system covering all assignments from 02 to 05. Each assignment builds upon the previous one, culminating in this full-featured voting application.

