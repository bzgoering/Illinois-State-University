#by Ben Goering

#Chess piece class that will validate regular piece moves; exludes castling, check, and en passente
from chess_utils import BoardInfo
from chess_utils import PieceInfo

class ChessPiece:
    def __init__(self, _row, _col, _color, _label):
        self._row = _row
        self._col = _col
        self._label = _label
        self._color = _color

    #getters
    def get_color(self):
        return self._color
    def get_label(self):
        return self._label
    
    #updates the peice's position
    def move(self,new_row,new_col):
        self._row = new_row
        self._col = new_col
    
    #base critia for all chess peice: new space must be on the space and same color cant be on it
    def is_legal_move(self,new_row,new_col,board):
        square_type = board.get_square_info(new_row, new_col)
        return square_type != BoardInfo.OFF_THE_BOARD and board.get_square_info(new_row,new_col) != self._color

    #adds itself, WILL be overwritten
    def generate_legal_moves(self,board_data, board):
        #add current spot on board
        board_data[self._row][self._col] = self._label.value
        return board_data

    #given a list of possible moves adds to board_data if possible
    def generate_help(self,moves,board_data,board):
        for row,col in moves:
            new_row = self._row + row
            new_col = self._col + col

            #ensures space is not out of bounds and occupied by friendly also alows current position
            if ChessPiece.is_legal_move(self,new_row,new_col,board) or (new_row == self._row and new_col == self._col):
                board_data[new_row][new_col] = self._label.value
        return board_data  

    #checks if certain spots on the board is occupied
    def is_occupied(self, row, col,board):
        return board.get_square_info(row,col) != BoardInfo.EMPTY

    def is_enemy(self, row, col,board):
        return board.get_square_info(row,col) == BoardInfo.BLACK

#knight can only move 2 dirrections one way and 1 dirrect the other creating an L
class Knight(ChessPiece):
    def __init__(self, _row, _col, _color, _label):
        super().__init__(_row, _col, _color, _label)

    def is_legal_move(self, new_row, new_col,board):
        if super().is_legal_move(new_row, new_col,board): #check to ensure move is on board
            row_diff = abs(new_row - self._row)
            col_diff = abs(new_col - self._col)

            if (row_diff == 2 and col_diff == 1) or (row_diff == 1 and col_diff == 2): #check to ensure move is L shaped
                if super().is_occupied(new_row, new_col,board):
                    return board.get_square_info(new_row, new_col) == BoardInfo.BLACK #allows knight to take black peices
                else:
                    return True #knight is jumping to a empty space
            return False

    def generate_legal_moves(self,board_data, board):
        #8 total possible moves
        moves = [(2,1),(2,-1),(-2,1),(-2,-1),(1,2),(1,-2),(-1,2),(-1,-2),(0, 0)]
        board_data = super().generate_help(moves,board_data,board)
        return board_data

#pawns can only move one space up or if at start up to 2 spaces forwards
class WhitePawn(ChessPiece):
    def __init__(self, _row, _col, _color, _label):
        super().__init__(_row, _col, _color, _label)

    def is_legal_move(self, new_row, new_col,board):        
        if super().is_legal_move(new_row, new_col,board):
            row_diff = new_row - self._row
            col_diff = abs(new_col - self._col)
            
            #pawn moved forwards
            if col_diff == 0:
                if row_diff == 2 and self._row == 1: #pawn goes 2 up from starting tile
                    return not super().is_occupied(new_row,new_col,board) and not super().is_occupied(new_row-1,new_col,board)#pawn moves 2 spots only from the start
                elif row_diff == 1: #defualt 1 tile push
                    return not super().is_occupied(new_row,new_col,board) #pawn moves 1 spot forwards
                else: return False
            #pawn takes piece
            if col_diff == 1 and row_diff == 1 and super().is_enemy(new_row, new_col,board):
                return True
                
        return False

    def generate_legal_moves(self,board_data, board):
        moves = [(0,0)]

        
        if super().is_enemy(self._row+1, self._col+1,board):
            moves.append((1,1))
        if super().is_enemy(self._row+1, self._col-1,board):
            moves.append((1,-1))
        if self._row == 1 and not super().is_enemy(self._row+2, self._col,board):
            moves.append((2,0))
        elif not super().is_enemy(self._row+1, self._col,board):
            moves.append((1,0))
        
        board_data = super().generate_help(moves,board_data,board)
        return board_data

#parent class for Rook and Bishop; used for its is_path_clear method
class slide(ChessPiece):
    def __init__(self, _row, _col, _color, _label):
        super().__init__(_row, _col, _color, _label)

    #validates move; ensuring its clear of pieces
    def is_path_clear(self, new_row, new_col, board, inc1,inc2):
        position = self.path(new_row, new_col, board, inc1,inc2)
        if position == []: return False

        pos_x,pos_y = position[-1]
        #gets actual positions
        pos_x += self._row
        pos_y += self._col

        #check if the last position found is the new position
        if pos_x == new_row and pos_y == new_col: return True
        return False #position unreachable with this piece

    #paths a dirrections and returns the increments
    def path(self, new_row, new_col, board, inc1,inc2):
        moves = []
        pos_x = self._row + inc1
        pos_y = self._col + inc2

        #goes towards new position checking its empty
        while pos_x != new_row or pos_y != new_col:
            if self.is_occupied(pos_x,pos_y,board): 
                if self.is_enemy(pos_x,pos_y,board):
                    moves.append((pos_x-self._row,pos_y-self._col))
                return moves
            else:
                moves.append((pos_x-self._row,pos_y-self._col))
            pos_x += inc1
            pos_y += inc2
        
        moves.append((new_row-self._row,new_col-self._col))
        return moves

#rooks can move striaght in one dirrection
class Rook(slide):
    def __init__(self, _row, _col, _color, _label):
        super().__init__(_row, _col, _color, _label)

    def is_legal_move(self, new_row, new_col,board):
        if ChessPiece.is_legal_move(self,new_row, new_col,board):
            row_diff = new_row - self._row
            col_diff = new_col - self._col
            
            #decides which dirrection the user wants to go up,down,left,right
            if (row_diff == 0 and col_diff != 0) or (row_diff != 0 and col_diff == 0):
                if row_diff > 0:
                    return slide.is_path_clear(self, new_row, new_col, board, 1, 0)
                elif row_diff < 0:
                    return slide.is_path_clear(self, new_row, new_col, board, -1, 0)
                elif col_diff > 0:
                    return slide.is_path_clear(self, new_row, new_col, board, 0, 1)
                elif col_diff < 0:
                    return slide.is_path_clear(self, new_row, new_col, board, 0, -1)

        #new spot isnt staight
        return False

    def generate_legal_moves(self,board_data, board):
        moves = [(0,0)]
        #gets valid spaces in all four dirrections
        moves.extend(super().path(self._row, 7, board,0 ,1))
        moves.extend(super().path(self._row, 0, board, 0, -1))
        moves.extend(super().path(7, self._col, board, 1, 0))
        moves.extend(super().path(0, self._col, board, -1, 0))

        #adds them to board if they are valid
        board_data = ChessPiece.generate_help(self,moves,board_data,board)
        return board_data
    

#bishops can move diangonally
class Bishop(slide):
    def __init__(self, _row, _col, _color, _label):
        super().__init__(_row, _col, _color, _label)

    def is_legal_move(self, new_row, new_col, board):
        if ChessPiece.is_legal_move(self,new_row, new_col, board):
            row_diff = new_row - self._row
            col_diff = new_col - self._col
            
            if abs(row_diff) != abs(col_diff): return False #ensures move is diagnal

            #calulate where the bishop is moving too
            if row_diff > 0:
                if col_diff > 0:
                    return slide.is_path_clear(self,new_row,new_col,board,1,1)
                else:
                    return slide.is_path_clear(self,new_row,new_col,board,1,-1)
            else:
                if col_diff > 0:
                    return slide.is_path_clear(self,new_row,new_col,board,-1,1)
                else:
                    return slide.is_path_clear(self,new_row,new_col,board,-1,-1)
    
    def generate_legal_moves(self,board_data, board):
        moves = [(0,0)]
        #gets valid spaces in all four dirrections
        moves.extend(super().path(7, 7, board,1 ,1))
        moves.extend(super().path(0, 0, board, -1, -1))
        moves.extend(super().path(7, 0, board, 1, -1))
        moves.extend(super().path(0, 7, board, -1, 1))

        #adds them to board if they are valid
        board_data = ChessPiece.generate_help(self,moves,board_data,board)
        return board_data

#queen can move either like a rook or bishop
class Queen(Rook,Bishop):
    def __init__(self, _row, _col, _color, _label):
        super().__init__(_row, _col, _color, _label)

    def is_legal_move(self, new_row, new_col,board):
        #check will moves that allign with rook or bishop valid moves
        rook_move = Rook.is_legal_move(self,new_row,new_col,board)
        bishop_move = Bishop.is_legal_move(self,new_row,new_col,board)
        return bishop_move or rook_move

    #generates from both rook and bishop
    def generate_legal_moves(self,board_data, board):
        board_data = Rook.generate_legal_moves(self,board_data, board)
        board_data = Bishop.generate_legal_moves(self,board_data, board)
        return board_data


