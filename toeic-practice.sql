USE [master]
GO
/****** Object:  Database [toeic_practice]    Script Date: 2/27/2022 7:37:16 PM ******/
CREATE DATABASE [toeic_practice]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'toeic_practice', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQL\MSSQL\DATA\toeic_practice.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'toeic_practice_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQL\MSSQL\DATA\toeic_practice_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [toeic_practice] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [toeic_practice].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [toeic_practice] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [toeic_practice] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [toeic_practice] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [toeic_practice] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [toeic_practice] SET ARITHABORT OFF 
GO
ALTER DATABASE [toeic_practice] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [toeic_practice] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [toeic_practice] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [toeic_practice] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [toeic_practice] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [toeic_practice] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [toeic_practice] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [toeic_practice] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [toeic_practice] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [toeic_practice] SET  ENABLE_BROKER 
GO
ALTER DATABASE [toeic_practice] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [toeic_practice] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [toeic_practice] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [toeic_practice] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [toeic_practice] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [toeic_practice] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [toeic_practice] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [toeic_practice] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [toeic_practice] SET  MULTI_USER 
GO
ALTER DATABASE [toeic_practice] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [toeic_practice] SET DB_CHAINING OFF 
GO
ALTER DATABASE [toeic_practice] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [toeic_practice] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [toeic_practice] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [toeic_practice] SET QUERY_STORE = OFF
GO
USE [toeic_practice]
GO
/****** Object:  Table [dbo].[tbl_answer]    Script Date: 2/27/2022 7:37:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_answer](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[question_id] [int] NULL,
	[active] [bit] NOT NULL,
	[correct] [bit] NOT NULL,
	[created_at] [smalldatetime] NOT NULL,
	[updated_at] [smalldatetime] NULL,
	[content] [varchar](300) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_answer_taken]    Script Date: 2/27/2022 7:37:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_answer_taken](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[quiz_taken_id] [int] NULL,
	[question_id] [int] NULL,
	[answer_id] [int] NULL,
	[created_at] [smalldatetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_level]    Script Date: 2/27/2022 7:37:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_level](
	[id] [smallint] IDENTITY(1,1) NOT NULL,
	[name] [varchar](30) NOT NULL,
	[description] [varchar](300) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [AK_quiz_level_name] UNIQUE NONCLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_part_section]    Script Date: 2/27/2022 7:37:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_part_section](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[content] [varchar](4000) NULL,
	[part_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_question]    Script Date: 2/27/2022 7:37:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_question](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[quiz_id] [int] NULL,
	[active] [bit] NOT NULL,
	[score] [smallint] NOT NULL,
	[created_at] [smalldatetime] NOT NULL,
	[updated_at] [smalldatetime] NULL,
	[title] [varchar](75) NULL,
	[content] [varchar](1000) NULL,
	[section_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_question_type]    Script Date: 2/27/2022 7:37:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_question_type](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [AK_question_type_name] UNIQUE NONCLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_quiz]    Script Date: 2/27/2022 7:37:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_quiz](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[admin_id] [int] NULL,
	[topic_id] [smallint] NULL,
	[title] [varchar](75) NOT NULL,
	[content] [varchar](1000) NULL,
	[slug] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [AK_quiz_title] UNIQUE NONCLUSTERED 
(
	[title] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_quiz_part]    Script Date: 2/27/2022 7:37:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_quiz_part](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[content] [varchar](150) NULL,
	[quiz_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_quiz_taken]    Script Date: 2/27/2022 7:37:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_quiz_taken](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [int] NULL,
	[quiz_id] [int] NULL,
	[scored] [smallint] NOT NULL,
	[created_at] [smalldatetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_role]    Script Date: 2/27/2022 7:37:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_role](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [AK_role_name] UNIQUE NONCLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_topic]    Script Date: 2/27/2022 7:37:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_topic](
	[id] [smallint] IDENTITY(1,1) NOT NULL,
	[name] [varchar](30) NOT NULL,
	[description] [varchar](300) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [AK_topic_name] UNIQUE NONCLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_user]    Script Date: 2/27/2022 7:37:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_user](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[email] [varchar](255) NOT NULL,
	[name] [nvarchar](255) NULL,
	[password] [nvarchar](255) NOT NULL,
	[role_id] [int] NOT NULL,
	[active] [bit] NOT NULL,
	[created_at] [smalldatetime] NOT NULL,
	[updated_at] [smalldatetime] NULL,
	[key_token] [varchar](255) NULL,
	[avatar] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [AK_unique_user_email] UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_wishlist]    Script Date: 2/27/2022 7:37:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_wishlist](
	[user_id] [int] NOT NULL,
	[quiz_id] [int] NOT NULL,
 CONSTRAINT [PK_wishlist] PRIMARY KEY CLUSTERED 
(
	[user_id] ASC,
	[quiz_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[tbl_answer] ADD  CONSTRAINT [df_answer_is_active]  DEFAULT ((1)) FOR [active]
GO
ALTER TABLE [dbo].[tbl_answer] ADD  CONSTRAINT [df_answer_is_incorrect]  DEFAULT ((0)) FOR [correct]
GO
ALTER TABLE [dbo].[tbl_answer] ADD  CONSTRAINT [df_answer_created_at]  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[tbl_answer_taken] ADD  CONSTRAINT [df_answer_taken_at]  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[tbl_question] ADD  CONSTRAINT [df_question_active]  DEFAULT ((1)) FOR [active]
GO
ALTER TABLE [dbo].[tbl_question] ADD  CONSTRAINT [df_question_score]  DEFAULT ((1)) FOR [score]
GO
ALTER TABLE [dbo].[tbl_question] ADD  CONSTRAINT [df_question_created_at]  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[tbl_quiz_taken] ADD  CONSTRAINT [df_min_score]  DEFAULT ((0)) FOR [scored]
GO
ALTER TABLE [dbo].[tbl_quiz_taken] ADD  CONSTRAINT [df_quiz_taken_at]  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[tbl_user] ADD  CONSTRAINT [df_is_active]  DEFAULT ((1)) FOR [active]
GO
ALTER TABLE [dbo].[tbl_user] ADD  CONSTRAINT [df_user_created_at]  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[tbl_answer]  WITH CHECK ADD  CONSTRAINT [FK_in_question] FOREIGN KEY([question_id])
REFERENCES [dbo].[tbl_question] ([id])
GO
ALTER TABLE [dbo].[tbl_answer] CHECK CONSTRAINT [FK_in_question]
GO
ALTER TABLE [dbo].[tbl_answer_taken]  WITH CHECK ADD  CONSTRAINT [FK_answer_of] FOREIGN KEY([question_id])
REFERENCES [dbo].[tbl_question] ([id])
GO
ALTER TABLE [dbo].[tbl_answer_taken] CHECK CONSTRAINT [FK_answer_of]
GO
ALTER TABLE [dbo].[tbl_answer_taken]  WITH CHECK ADD  CONSTRAINT [FK_answer_taken] FOREIGN KEY([answer_id])
REFERENCES [dbo].[tbl_answer] ([id])
GO
ALTER TABLE [dbo].[tbl_answer_taken] CHECK CONSTRAINT [FK_answer_taken]
GO
ALTER TABLE [dbo].[tbl_answer_taken]  WITH CHECK ADD  CONSTRAINT [FK_quiz_taken_id] FOREIGN KEY([quiz_taken_id])
REFERENCES [dbo].[tbl_quiz_taken] ([id])
GO
ALTER TABLE [dbo].[tbl_answer_taken] CHECK CONSTRAINT [FK_quiz_taken_id]
GO
ALTER TABLE [dbo].[tbl_part_section]  WITH CHECK ADD  CONSTRAINT [FK_part_id] FOREIGN KEY([part_id])
REFERENCES [dbo].[tbl_quiz_part] ([id])
GO
ALTER TABLE [dbo].[tbl_part_section] CHECK CONSTRAINT [FK_part_id]
GO
ALTER TABLE [dbo].[tbl_question]  WITH CHECK ADD  CONSTRAINT [FK_in_quiz] FOREIGN KEY([quiz_id])
REFERENCES [dbo].[tbl_quiz] ([id])
GO
ALTER TABLE [dbo].[tbl_question] CHECK CONSTRAINT [FK_in_quiz]
GO
ALTER TABLE [dbo].[tbl_question]  WITH CHECK ADD  CONSTRAINT [FK_question_section] FOREIGN KEY([section_id])
REFERENCES [dbo].[tbl_part_section] ([id])
GO
ALTER TABLE [dbo].[tbl_question] CHECK CONSTRAINT [FK_question_section]
GO
ALTER TABLE [dbo].[tbl_quiz]  WITH CHECK ADD  CONSTRAINT [FK_quiz_created_by] FOREIGN KEY([admin_id])
REFERENCES [dbo].[tbl_user] ([id])
GO
ALTER TABLE [dbo].[tbl_quiz] CHECK CONSTRAINT [FK_quiz_created_by]
GO
ALTER TABLE [dbo].[tbl_quiz]  WITH CHECK ADD  CONSTRAINT [FK_quiz_in_topic] FOREIGN KEY([topic_id])
REFERENCES [dbo].[tbl_topic] ([id])
GO
ALTER TABLE [dbo].[tbl_quiz] CHECK CONSTRAINT [FK_quiz_in_topic]
GO
ALTER TABLE [dbo].[tbl_quiz_part]  WITH CHECK ADD  CONSTRAINT [FK_quiz_id] FOREIGN KEY([quiz_id])
REFERENCES [dbo].[tbl_quiz] ([id])
GO
ALTER TABLE [dbo].[tbl_quiz_part] CHECK CONSTRAINT [FK_quiz_id]
GO
ALTER TABLE [dbo].[tbl_quiz_taken]  WITH CHECK ADD  CONSTRAINT [FK_quiz_taken_by] FOREIGN KEY([user_id])
REFERENCES [dbo].[tbl_user] ([id])
GO
ALTER TABLE [dbo].[tbl_quiz_taken] CHECK CONSTRAINT [FK_quiz_taken_by]
GO
ALTER TABLE [dbo].[tbl_quiz_taken]  WITH CHECK ADD  CONSTRAINT [FK_taken_quiz] FOREIGN KEY([quiz_id])
REFERENCES [dbo].[tbl_quiz] ([id])
GO
ALTER TABLE [dbo].[tbl_quiz_taken] CHECK CONSTRAINT [FK_taken_quiz]
GO
ALTER TABLE [dbo].[tbl_user]  WITH CHECK ADD  CONSTRAINT [FK_user_role] FOREIGN KEY([role_id])
REFERENCES [dbo].[tbl_role] ([id])
GO
ALTER TABLE [dbo].[tbl_user] CHECK CONSTRAINT [FK_user_role]
GO
ALTER TABLE [dbo].[tbl_wishlist]  WITH CHECK ADD  CONSTRAINT [FK_favor_quiz] FOREIGN KEY([quiz_id])
REFERENCES [dbo].[tbl_quiz] ([id])
GO
ALTER TABLE [dbo].[tbl_wishlist] CHECK CONSTRAINT [FK_favor_quiz]
GO
ALTER TABLE [dbo].[tbl_wishlist]  WITH CHECK ADD  CONSTRAINT [FK_user_has_favor_quiz] FOREIGN KEY([user_id])
REFERENCES [dbo].[tbl_user] ([id])
GO
ALTER TABLE [dbo].[tbl_wishlist] CHECK CONSTRAINT [FK_user_has_favor_quiz]
GO
USE [master]
GO
ALTER DATABASE [toeic_practice] SET  READ_WRITE 
GO
