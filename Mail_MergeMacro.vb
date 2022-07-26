Attribute VB_Name = "Module1"
Option Explicit

Const FOLDER_SAVED As String = "<Detination Folder>"
'This is where you will choose the destination of the files
Const SOURCE_FILE_PATH As String = "<Source File>"
'This is where you will select the source file, which is an excel file

Sub MacroName()
'This is where you will enter the name of your macro

Dim MainDoc As Document, TargetDoc As Document
Dim dbPath As String
Dim recordNumber As Long, totalRecord As Long

Set MainDoc = ActiveDocument
With MainDoc.MailMerge
    
        'Enter the worksheet name, better to be specific than run the whole workbook
        .OpenDataSource Name:=SOURCE_FILE_PATH, sqlstatement:="SELECT * FROM [<Sheet Name>$]"
            
        totalRecord = .DataSource.RecordCount

        For recordNumber = 1 To totalRecord
        
            With .DataSource
                .ActiveRecord = recordNumber
                .FirstRecord = recordNumber
                .LastRecord = recordNumber
            End With
            
            .Destination = wdSendToNewDocument
            .Execute False
            
            Set TargetDoc = ActiveDocument

            'Tip: Use _ to continue code into next line for cleaner code
            TargetDoc.ExportAsFixedFormat FOLDER_SAVED _
            & .DataSource.DataFields("Borrower_Name").Value _
            & .DataSource.DataFields("Loan_Number").Value _
            & ".pdf", exportformat:=wdExportFormatPDF
            
            'This will export as a PDF format and use the variables that were merged to name the document
            
            TargetDoc.Close False
            
            Set TargetDoc = Nothing
                    
        Next recordNumber

End With

Set MainDoc = Nothing
End Sub
